package com.example.ecom.repo;

import com.example.ecom.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userRepo extends MongoRepository<User,String> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
}
