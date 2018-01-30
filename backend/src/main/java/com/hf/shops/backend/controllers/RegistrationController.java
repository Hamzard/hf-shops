package com.hf.shops.backend.controllers;

import com.hf.shops.backend.entities.Role;
import com.hf.shops.backend.entities.User;
import com.hf.shops.backend.pojos.UserRegistration;
import com.hf.shops.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/register")
    public ResponseEntity<?> register(@RequestBody UserRegistration userRegistration){
        if(userService.findByUsername(userRegistration.getUsername()) != null)
            return ResponseEntity.badRequest().body("Email already in use");

        userService.save(new User(userRegistration.getUsername(),
                userRegistration.getPassword(),
                Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));

        return ResponseEntity.ok().body("User created");
    }
}