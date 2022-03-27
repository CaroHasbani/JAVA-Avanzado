package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private FeignRepository feignRepository;

    @GetMapping
    List<Product> getProducts(){
        return this.feignRepository.getProducts();
    }

    @GetMapping("/{productId}")
    Product getProductById(@PathVariable Long productId){
           return this.feignRepository.getProductById(productId);
        
    }

    @PostMapping("/add")
    Product addProduct(@RequestBody Product product){
        return this.feignRepository.addProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    void deleteProduct(@PathVariable Long id){
        this.feignRepository.deleteProduct(id);
      
    }
    @PutMapping("/update/{id}")
    Product updateProduct(@PathVariable Long id, @RequestBody Product newProduct){
        return this.feignRepository.updateProduct(id,newProduct);
    }
}