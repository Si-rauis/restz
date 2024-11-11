package edu.example.restz.dto;

import edu.example.restz.entity.Cart;
import edu.example.restz.entity.CartItem;
import edu.example.restz.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long itemNo;
    @Min(0)
    private int quantity;
    private Long pno;
    private int price;
    private String pname;
    private List<String> images;
    private int cartNum;
    private String customer;


    public CartDTO(CartItem cartItem) {
        this.itemNo = cartItem.getItemNo();
        this.quantity = cartItem.getQuantity();
        this.pno = cartItem.getProduct().getPno();
        this.price = cartItem.getProduct().getPrice();
        this.pname = cartItem.getProduct().getPname();
        this.cartNum = cartItem.getCart().getCartNum();
        this.customer = cartItem.getCart().getCustomer();
        this.images = new ArrayList<>();
        cartItem.getProduct().getImages().forEach(image -> this.images.add(image.getFilename()));
    }

    public CartItem toEntity(){
        Product product = Product.builder().pno(pno).build();

        return CartItem.builder()
                        .product(product)
                        .quantity(quantity)
                        .build();
    }
}
