package edu.example.restz.repository;

import edu.example.restz.dto.ReviewDTO;
import edu.example.restz.entity.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.SortedSet;
import java.util.stream.IntStream;

import static edu.example.restz.entity.QCart.cart;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Log4j2
public class CartItemRepositoryTests {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Test   //insert 테스트
    public void testInsert(){
        Long pno = 1L;
        Product product = Product.builder().pno(pno).build();

        IntStream.rangeClosed(1, 5).forEach(i -> {
            String customer = "user" + i;

            if(!cartRepository.getCart(customer).isPresent()){
                Cart cart = Cart.builder().customer(customer).build();
                cartRepository.save(cart);
            }
                Cart cart = cartRepository.getCart(customer).get();


            CartItem cartItem = CartItem.builder()
                                        .quantity(5)
                                        .product(product)
                                        .cart(cart)
                                        .build();

            //WHEN - 엔티티 저장
            CartItem savedCartItem = cartItemRepository.save(cartItem);

            //THEN - savedProduct가 널이 아니고 mno는 1일 것
            assertNotNull(savedCartItem);
            assertEquals(i, savedCartItem.getItemNo());
            assertEquals(1, savedCartItem.getProduct().getPno());
        });
    }
}











