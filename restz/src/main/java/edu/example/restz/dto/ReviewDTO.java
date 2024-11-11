package edu.example.restz.dto;

import edu.example.restz.entity.Product;
import edu.example.restz.entity.Review;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long rno;               //리뷰 번호
    private String content;         //리뷰 내용
    @Min(1)
    @Max(5)
    private int star;               //리뷰 점수
    private String reviewer;        //리뷰 등록자
    private Long pno;               //상품 번호
    private LocalDateTime regDate;  //리뷰 등록 일시
    private LocalDateTime modDate;  //리뷰 수정 일시

    public ReviewDTO(Review review) {
        this.rno = review.getRno();
        this.content = review.getContent();
        this.star = review.getStar();
        this.reviewer = review.getReviewer();
        this.pno = review.getProduct().getPno();
        this.regDate = review.getRegDate();
        this.modDate = review.getModDate();
    }

    public Review toEntity(){
        Product product = Product.builder().pno(pno).build();

        return Review.builder()
                        .rno(rno)
                        .content(content)
                        .star(star)
                        .reviewer(reviewer)
                        .product(product)
                        .build();
    }
}
