package edu.example.restz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name= "tbl_cart", indexes = @Index(columnList = "customer"))
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;

    private String customer;

    private int CartNum;

    @LastModifiedDate
    private LocalDateTime mod_date;

    @CreatedDate
    private LocalDateTime reg_date;

    public void changeCartNum(int cartNum) {
        CartNum = cartNum;
    }

    public void updateModDate() {
        this.mod_date = LocalDateTime.now();
    }
}
