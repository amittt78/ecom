package com.example.ecom.controller;

import com.example.ecom.model.User;
import com.example.ecom.model.product;
import com.example.ecom.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class productController {

    @Autowired
    private productService service;

    @RequestMapping("/")
    public String greet(){
        return "Hello";
    }

    @GetMapping("/products")
    public ResponseEntity< List<product>> getAllProduct(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<product> getProductById(@PathVariable String id){
        product prod=service.getProductById(id);
        if(prod!=null) {
            return new ResponseEntity<>(prod, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody product prod){
        try{
            product prod1=service.addProduct(prod);
            return new ResponseEntity<>(prod1,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

/*
    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable String id){
        product prod=service.getProductById(id);
        byte[] imageFile=prod.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(prod.getImageType()))
                .body(imageFile);
    }
*/

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable String id,@RequestBody product prod){
        product existing = service.getProductById(id);

        if(existing == null){
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
        prod.setId(id);
        product updated = service.updateProduct(prod);
        return new ResponseEntity<>("updated", HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id){
        product prod=service.getProductById(id);
        if(prod!=null){
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }else{
            return  new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(service.getAllUser(),HttpStatus.OK);

    }

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username){
        User user=service.getUserByName(username);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody User use){
        try {
            User savedUser = service.addUser(use);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody User use){
        User use1=service.verifyUser(use.getUsername(),use.getPassword());
        if(use1!=null){
            return new ResponseEntity<>(use1,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("User Not Existed",HttpStatus.OK);
        }

    }
}
