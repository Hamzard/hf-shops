package com.hf.shops.backend.controllers;

import com.hf.shops.backend.config.CustomUserDetails;
import com.hf.shops.backend.entities.Shop;
import com.hf.shops.backend.services.ShopService;
import com.hf.shops.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/preferred")
public class PreferredShopsController {

    private ShopService shopService;
    private UserService userService;

    @Autowired
    public PreferredShopsController(ShopService shopService, UserService userService) {
        this.shopService = shopService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getPreferredShops() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Set<Shop> preferred_shops_buffer = this.userService.getPreferredShops(username);
        if(preferred_shops_buffer.size() > 0){
            return ResponseEntity.ok().body(preferred_shops_buffer);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> addToPreferred(@PathVariable("id") String id) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Shop shop = shopService.findById(id);
        userService.addToPreferred(username, shop);
        return ResponseEntity.ok().body("Shop added");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeFromPreferred(@PathVariable("id") String id) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Shop shop = shopService.findById(id);
        userService.removeFromPreferred(username, shop);
        return ResponseEntity.ok().body("Shop removed");
    }
}