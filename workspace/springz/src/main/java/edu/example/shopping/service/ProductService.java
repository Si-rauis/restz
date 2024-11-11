package edu.example.shopping.service;


import edu.example.shopping.entity.Product;

import java.util.List;

public interface ProductService {
   Product findById(String productId);

   List<Product> findAll();
}
