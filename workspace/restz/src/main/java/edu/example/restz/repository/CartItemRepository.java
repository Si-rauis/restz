package edu.example.restz.repository;

import edu.example.restz.dto.CartDTO;
import edu.example.restz.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT ci FROM CartItem ci JOIN FETCH ci.cart JOIN FETCH ci.product WHERE ci.cart.cno = :cno")
    Optional<List<CartDTO>> getCartDTO(@Param("cno") Long cno);
}

