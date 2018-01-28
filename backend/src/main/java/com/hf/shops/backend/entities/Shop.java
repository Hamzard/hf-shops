package com.hf.shops.backend.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "shops")
public class Shop {
    @Id
    private String id;
    private String picture;
    private String name;
    private String email;
    private String city;
    @GeoSpatialIndexed(type= GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;

    public Shop() { super(); }

    public Shop(String picture, String name, String email, String city, GeoJsonPoint location) {
        this.picture = picture;
        this.name = name;
        this.email = email;
        this.city = city;
        this.location = location;
    }

    public String getId() {
        return id;
    }
    public String getPicture() {
        return picture;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getCity() {
        return city;
    }
    public GeoJsonPoint getLocation() {
        return location;
    }
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Shop)) {
            return false;
        }
        Shop shop = (Shop) o;
        return id.equals(shop.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}