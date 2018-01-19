package com.hf.shops.backend.controllers;

import com.hf.shops.backend.config.CustomUserDetails;
import com.hf.shops.backend.entities.Shop;
import com.hf.shops.backend.services.ShopService;
import com.hf.shops.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferred")
public class PreferredShopsController {

    private ShopService shopService;
    private UserService userService;

    @Autowired
    public PreferredShopsController(ShopService shopService, UserService userService) {
        this.shopService = shopService;
        this.userService = userService;
    }

    @GetMapping
    public List<Shop> getPreferredShops() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        return this.userService.getPreferredShops(username);
    }

    @PutMapping("/{id}")
    public void addToPreferred(@PathVariable("id") String id) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Shop shop = shopService.findById(id);
        userService.addToPreferred(username, shop);
    }

    @DeleteMapping("/{id}")
    public void removeFromPreferred(@PathVariable("id") String id) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        userService.removeFromPreferred(username, id);
    }
}