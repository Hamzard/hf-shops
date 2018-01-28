package com.hf.shops.backend.services;

import com.hf.shops.backend.entities.Shop;
import com.hf.shops.backend.entities.User;
import com.hf.shops.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
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
