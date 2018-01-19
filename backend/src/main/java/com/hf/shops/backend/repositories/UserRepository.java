package com.hf.shops.backend.repositories;

import com.hf.shops.backend.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(final String username);

}