package com.example.ICE.controller;

import Models.Product;
import com.example.ICE.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/search/")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.allProducts(), HttpStatus.OK);
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<List<Product>> queryProducts(@PathVariable String query) {
        return new ResponseEntity<>(productService.queryProducts(query), HttpStatus.OK);
    }
}
