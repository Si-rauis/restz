package edu.example.restz.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductListDTO {
    private Long reviewCount;
    private long pno;
    private String pname;
    private int price;
    private String registerId;
    private String pimage;
}
