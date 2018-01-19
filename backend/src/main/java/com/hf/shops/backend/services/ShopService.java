package com.hf.shops.backend.services;

import com.hf.shops.backend.entities.Shop;
import com.hf.shops.backend.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private ShopRepository shopRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository){
        this.shopRepository = shopRepository;
    }

    public List<Shop> getAll(){
        return this.shopRepository.findAll();
    }

    public Shop findById(String id){
        return this.shopRepository.findOne(id);
    }

    public List<Shop> findByLocationNear(double longitude, double latitude){
        Point point = new Point(longitude, latitude);
        return this.shopRepository.findByLocationNear(point);
    }

    public List<Shop> findByLocationNear(double longitude, double latitude, int page, int page_size){
        Point point = new Point(longitude, latitude);
        return this.shopRepository.findByLocationNear(point, new PageRequest(page, page_size));
    }

}