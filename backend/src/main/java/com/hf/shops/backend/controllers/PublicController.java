package com.hf.shops.backend.controllers;

import com.hf.shops.backend.entities.Shop;
import com.hf.shops.backend.services.ShopService;
import com.hf.shops.backend.services.UserService;
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

    private UserService userService;

    @Autowired
    public PublicController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> fun(){
        List<Shop> most_liked_shops_buffer = this.userService.getMostLikedShops();
        if(most_liked_shops_buffer.size() > 0){
            return ResponseEntity.ok().body(most_liked_shops_buffer);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}