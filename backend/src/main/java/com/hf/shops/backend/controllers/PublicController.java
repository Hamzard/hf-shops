package com.hf.shops.backend.controllers;

import com.hf.shops.backend.entities.Shop;
import com.hf.shops.backend.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Shop> getNearbyShopsPublic(@RequestParam double lon , @RequestParam double lat, @RequestParam int p, @RequestParam int s){
        // lon: longitude
        // lat: latitude
        // p: page
        // s: number of elements that must be in the page
        return shopService.findByLocationNear(lon,lat, p, s);
    }
}