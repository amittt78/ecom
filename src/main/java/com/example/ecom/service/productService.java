package com.example.ecom.service;

import com.example.ecom.model.User;
import com.example.ecom.model.product;
import com.example.ecom.repo.productRepo;
import com.example.ecom.repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService {
    @Autowired
    productRepo repo;
    @Autowired
    userRepo rep;

    public List<product> getAllProducts() {
     return repo.findAll();

    }

    public product getProductById(String id) {
        return repo.findById(id).orElse(null);
    }

    public product addProduct(product prod) {
        return repo.save(prod);
    }

    public product updateProduct(product prod)  {
//        prod.setImageData(imageFile.getBytes());
//        prod.setImageName(imageFile.getOriginalFilename());
//        prod.setImageType(imageFile.getContentType());

        return repo.save(prod);
    }

    public void deleteProduct(String id) {
         repo.deleteById(id);
    }

    public List<User> getAllUser() {
        return rep.findAll();
    }

    public User getUserByName(String username) {
        return rep.findByUsername(username);
    }

    public User addUser(User use) {
        if(rep.findByUsername(use.getUsername())!=null){
            throw new RuntimeException("UserName Already Exists");

        }
        return rep.save(use);
    }



    public User verifyUser(String username, String password) {
        return rep.findByUsernameAndPassword(username,password);
    }
}
