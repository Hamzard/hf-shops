package com.hf.shops.backend.controllers;

import com.hf.shops.backend.config.CustomUserDetails;
import com.hf.shops.backend.entities.Shop;
import com.hf.shops.backend.entities.User;
import com.hf.shops.backend.services.ShopService;
import com.hf.shops.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nearby")
public class NearbyShopsController {

    private ShopService shopService;
    private UserService userService;

    @Autowired
    public NearbyShopsController(ShopService shopService, UserService userService) {
        this.shopService = shopService;
        this.userService = userService;
    }

    @GetMapping
    public List<Shop> getNearbyShops(@RequestParam double lon , @RequestParam double lat, @RequestParam int p, @RequestParam int s){
        // lon: longitude
        // lat: latitude
        // p: page
        // s: number of elements that must be in the page
        //get authenticated user
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.findByUsername(username);

        //get nearby shops and filter user's preferred and disliked shops out
        LinkedHashMap<String, Shop> user_preferred = user.getPreferredShops();
        LinkedHashMap<String, LocalDateTime> user_disliked = user.getDislikedShops();
        LocalDateTime timeref = LocalDateTime.now();
        List<Shop> nearby_shops_buffer = shopService.findByLocationNear(lon,lat, p, s).stream()
                //filter out disliked shops
                .filter(x -> !user_disliked.containsKey(x.getId()) || Duration.between(user_disliked.get(x.getId()), timeref).toHours() >= 2)
                //filter out prefered shops
                .filter(x -> !user_preferred.containsKey(x.getId()))
                .collect(Collectors.toList());

        return nearby_shops_buffer;
    }

    @DeleteMapping("/{id}")
    public void dislike(@PathVariable("id") String id){
        //get the logged in user
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        this.userService.dislike(username, id);
    }
}
