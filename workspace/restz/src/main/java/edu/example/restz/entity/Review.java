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
@Table(name="tbl_review", indexes = @Index(columnList = "product_pno"))
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //- 1씩 자동 증가
    private Long rno;               //리뷰 번호

    private String content;         //리뷰 내용
    private int star;               //리뷰 점수
    private String reviewer;        //리뷰 등록자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_pno")
    private Product product;

    @CreatedDate
    private LocalDateTime regDate;  //리뷰 등록 일시

    @LastModifiedDate
    private LocalDateTime modDate;  //리뷰 수정 일시


    public void changeContent(String content) {
        this.content = content;
    }

    public void changeStar(int star) {
        this.star = star;
    }
}
