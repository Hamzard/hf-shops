package com.hf.shops.backend.services;

import com.hf.shops.backend.entities.LikedShop;
import com.hf.shops.backend.entities.Shop;
import com.hf.shops.backend.entities.User;
import com.hf.shops.backend.repositories.UserRepository;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregationOptions;

@Service
public class UserService {

    private UserRepository userRepository;
    private MongoOperations mongoOperations;

    @Autowired
    public UserService(UserRepository userRepository,
                       MongoOperations mongoOperations) {
        this.userRepository = userRepository;
        this.mongoOperations = mongoOperations;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public List<Shop> getMostLikedShops () {
        MapReduceResults<LikedShop > results = mongoOperations.mapReduce("users", "classpath:map-reduce/map.js", "classpath:map-reduce/reduce.js", LikedShop.class);
        List<Shop> buffer = new ArrayList<>();
        for(LikedShop  item : results) {
            //System.out.println(valueObject.getId().getName() + valueObject.getValue());
            buffer.add(item.getId());
        }
        return buffer;
    }

    public void save(User user){
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        //user.setPassword(user.getPassword());
        this.userRepository.save(user);
    }

    public long count(){
        return this.userRepository.count();
    }

    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public Set<Shop> getPreferredShops(String username){
        User user_tmp = this.userRepository.findByUsername(username);
        return user_tmp.getPreferredShops();
    }

    public void dislike(String username, String id){
        User user_tmp = this.userRepository.findByUsername(username);
        LinkedHashMap<String,LocalDateTime> disliked_shops = user_tmp.getDislikedShops();
        disliked_shops.put(id,LocalDateTime.now());
        user_tmp.setDislikedShops(disliked_shops);
        this.userRepository.save(user_tmp);
    }

    public void addToPreferred(String username, Shop shop){
        User user_tmp = this.userRepository.findByUsername(username);
        Set<Shop> preferredShops = user_tmp.getPreferredShops();
        preferredShops.add(shop);
        user_tmp.setPreferredShops(preferredShops);
        this.userRepository.save(user_tmp);
    }

    public void removeFromPreferred(String username, Shop shop){
        User user_tmp = this.userRepository.findByUsername(username);
        Set<Shop> preferredShops = user_tmp.getPreferredShops();
        preferredShops.remove(shop);
        user_tmp.setPreferredShops(preferredShops);
        this.userRepository.save(user_tmp);
    }

}
