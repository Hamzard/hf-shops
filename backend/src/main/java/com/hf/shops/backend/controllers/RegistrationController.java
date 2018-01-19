package com.hf.shops.backend.controllers;

import com.hf.shops.backend.entities.Role;
import com.hf.shops.backend.entities.User;
import com.hf.shops.backend.entities.UserRegistration;
import com.hf.shops.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.regex.Pattern;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserRegistration userRegistration){
        //Checking for non alphanumerical characters in the username.
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        if(pattern.matcher(userRegistration.getUsername()).find())
            return "No special characters are allowed in the username";

        if(userService.findByUsername(userRegistration.getUsername()) != null)
            return "Error this username already exists";

        userService.save(new User(userRegistration.getUsername(),
                userRegistration.getPassword(),
                Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
        return "User created";
    }
}