package com.hf.shops.backend.repositories;

import com.hf.shops.backend.entities.Shop;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends MongoRepository<Shop, String> {

    List<Shop> findByLocationNear(Point point);
    List<Shop> findByLocationNear(Point point, Pageable pageable);

}