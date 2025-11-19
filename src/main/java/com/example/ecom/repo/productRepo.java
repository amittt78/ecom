package com.example.ecom.repo;

import com.example.ecom.model.product;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepo extends MongoRepository<product,String> {
}
