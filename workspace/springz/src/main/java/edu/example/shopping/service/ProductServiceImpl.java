package edu.example.shopping.service;

import edu.example.shopping.entity.Product;
import edu.example.shopping.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(String productId) {
        return productRepository.selectById(productId);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.selectAll();
    }
}
