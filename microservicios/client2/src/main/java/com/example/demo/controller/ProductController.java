package com.example.demo.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;



@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

   @GetMapping("/{productId}")
    Optional<Product> getProductById(@PathVariable Long productId){
       return productRepository.findById(productId);
   }

   @GetMapping
    Iterable<Product> getProducts(){
      return productRepository.findAll();
   }

   @PostMapping("/add")
   Product addProduct(@RequestBody Product product){
       return productRepository.save(product);
   }

   @DeleteMapping("/delete/{id}")
    void deleteProduct(@PathVariable Long id){
	   productRepository.deleteById(id);
   }
   

}