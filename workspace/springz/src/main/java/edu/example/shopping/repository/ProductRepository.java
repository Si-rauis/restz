package edu.example.shopping.repository;

import edu.example.shopping.entity.Product;

import java.util.List;

public interface ProductRepository {
    Product selectById(String id);
    List<Product> selectAll();
    boolean update(Product product);
}
