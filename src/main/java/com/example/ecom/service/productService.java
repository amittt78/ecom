package com.example.ecom.service;

import com.example.ecom.model.product;
import com.example.ecom.repo.productRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class productService {
    @Autowired
    productRepo repo;

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
}
