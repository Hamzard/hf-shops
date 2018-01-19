package com.hf.shops.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private List<Role> roles;

    private LinkedHashMap<String,Shop> preferredShops;
    private LinkedHashMap<String, LocalDateTime> dislikedShops;

    public User(){super();}

    public User(String username, String password, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.preferredShops = new LinkedHashMap<String,Shop>();
        this.dislikedShops = new LinkedHashMap<String, LocalDateTime>();
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public LinkedHashMap<String, Shop> getPreferredShops() {
        return preferredShops;
    }

    public void setPreferredShops(LinkedHashMap<String, Shop> preferredShops) {
        this.preferredShops = preferredShops;
    }

    public LinkedHashMap<String, LocalDateTime> getDislikedShops() {
        return dislikedShops;
    }

    public void setDislikedShops(LinkedHashMap<String, LocalDateTime> dislikedShops) {
        this.dislikedShops = dislikedShops;
    }

}
