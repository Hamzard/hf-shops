package com.hf.shops.backend.controllers;

import com.hf.shops.backend.entities.Shop;
import com.hf.shops.backend.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    private ShopService shopService;

    @Autowired
    public PublicController(ShopService shopService) {
        this.shopService = shopService;
    }
    
    @GetMapping
    public ResponseEntity<?> getNearbyShopsPublic(@RequestParam double lon , @RequestParam double lat, @RequestParam int p, @RequestParam int s){
        // lon: longitude
        // lat: latitude
        // p: page
        // s: number of elements that must be in the page
        List<Shop> nearby_shops_buffer = shopService.findByLocationNear(lon,lat, p, s);
        if(nearby_shops_buffer.size() > 0){
            return ResponseEntity.ok().body(nearby_shops_buffer);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}