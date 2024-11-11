package edu.example.restz.dto;

import edu.example.restz.entity.Product;
import edu.example.restz.entity.ProductImage;
import edu.example.restz.entity.QProduct;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class ProductDTO {
    //1. 필드 6개 선언
    private Long reviewCount;
    private Long pno;               //상품 번호

    @NotEmpty
    private String pname;           //상품 이름

    @Min(0)
    private int price;              //상품 가격

    private String description;     //상품 설명

    @NotEmpty
    private String registerId;      //상품 등록자


    private List<String> images;    //이미지 파일이름 목록


    //2. Product를 매개변수로 받아서
    //현재 객체의 필드들을 초기화하는 생성자

    public ProductDTO(Product product) {
        this.pno = product.getPno();
        this.pname = product.getPname();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.registerId = product.getRegisterId();
        this.images = new ArrayList<>();
        product.getImages().forEach(image -> this.images.add(image.getFilename()));
    }


    //3. Product 객체의 builder()를 이용하여
    //  현재 객체의 값을 Product에 저장한 후
    //  반환하는 toEntity()메서드

    public Product toEntity(){
        Product product = Product.builder().pno(pno)
                                 .pname(pname)
                                 .price(price)
                                 .description(description)
                                 .registerId(registerId)
                                 .build();

        //  단, 상품이미지들이 있는 경우에는
        //  Product에 추가하여 반환하도록 처리
        if(images != null || !images.isEmpty()){
            for(String name: images){
                product.addImage(name);
            }
        }
        return product;
    }




}
