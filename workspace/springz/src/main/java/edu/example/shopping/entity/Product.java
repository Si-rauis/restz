package edu.example.shopping.entity;

import lombok.Data;


@Data
public class Product {
    private String id;
    private String name;
    private Integer price;
    private Integer quantity;
}
