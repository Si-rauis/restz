package edu.example.shopping.controller;


import edu.example.shopping.entity.Product;
import edu.example.shopping.service.ProductService;
import edu.example.training.entity.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor //생성자 필요 X
@RequestMapping("/api/product")
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("/api/product")
    public List<Product> getProducts(){
        List<Product> list = productService.findAll();
        return list;
    }

    @GetMapping("/api/product/{id}")
    public Product getProduct(@PathVariable String id){
        return productService.findById(id);
    }
}
