package edu.example.restz.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity                     //1.엔티티 클래스로 만들기
@Table(name="tbl_cart_item", indexes = @Index(columnList = "cart_cno"))
@Getter
@ToString(exclude = {"product","cart"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemNo;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public void changeQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void changeCart(Cart cart) {
        this.cart = cart;
    }
}
