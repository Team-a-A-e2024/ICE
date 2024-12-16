package com.example.ICE.service;

import Model.Product;
import Persistens.ProductRepo;
import org.springframework.stereotype.Service;
import util.ApiService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> products = ProductRepo.loadProducts();

    public List<Product> allProducts() {
        return products;
    }

    public List<Product> queryProducts(String query) {
        List<Product> result = new ArrayList<>();

        ApiService.searchProduct(query, 1);

        for(Product p : products) {
            if (p.getName().toLowerCase().contains(query.toLowerCase())) {
                result.add(p);
            }
        }

        return result;
    }
}
