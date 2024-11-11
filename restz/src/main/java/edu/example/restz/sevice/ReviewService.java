package edu.example.restz.sevice;



import edu.example.restz.dto.ReviewDTO;
import edu.example.restz.dto.ReviewPageRequestDTO;
import edu.example.restz.entity.Review;
import edu.example.restz.exception.ProductException;
import edu.example.restz.exception.ReviewException;
import edu.example.restz.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service                    //1. 빈 등록
@RequiredArgsConstructor    //2. 생성자
@Transactional              //3. 트랜잭션
@Log4j2
public class ReviewService {
    public final ReviewRepository reviewRepository;

    //등록
    public ReviewDTO register(ReviewDTO reviewDTO) {
        try {
            Review review = reviewDTO.toEntity();
            reviewRepository.save(review);
            //상품 등록시 예외가 발생한 경우
            return new ReviewDTO(review);
        }catch (DataIntegrityViolationException e) {
            throw ReviewException.PRODUCT_NOT_FOUND.get();
        }
        catch (Exception e) {
            //예외 메시지를 Product NOT Registered로 지정하여
            //ProductTaskException 발생시키기
            log.error("--- " + e.getMessage());
            throw ProductException.NOT_REGISTERED.get();
        }
    }

    //조회
    public ReviewDTO read(Long rno){
        Review review = reviewRepository.findById(rno)
                                        .orElseThrow(ReviewException.NOT_FOUND::get);
        return new ReviewDTO(review);
    }

    //수정
    public ReviewDTO modify(ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(reviewDTO.getRno())
                                        .orElseThrow(ReviewException.NOT_FOUND::get);

        if(!review.getReviewer().equals(reviewDTO.getReviewer())){
            throw ReviewException.NOT_MATCHED_REVIEWER.get();
        }

        //필요한 부분 수정 - 변경이 감지되면 수정 처리 수행
        try {
            //상품 이름, 가격, 설명 수정
            review.changeStar(review.getStar());
            review.changeContent(review.getContent());
            return new ReviewDTO(review);
        } catch (Exception e) {
            throw ProductException.NOT_MODIFIED.get();
        }
    }

    //삭제
    public void remove(Long rno, String reviewer) {
        Review review = reviewRepository.findById(rno)
                .orElseThrow(ReviewException.NOT_FOUND::get);

        if(!review.getReviewer().equals(reviewer)) {
            throw ReviewException.REGISTER_ERR.get();
        }

        try {
            //3. pno에 해당하는 데이터 삭제 메서드 호출
            reviewRepository.deleteById(rno);
        } catch (Exception e) {
            log.error("--- " + e.getMessage());
            throw ReviewException.NOT_REMOVED.get();
        }
    }

    //목록
    public Page<ReviewDTO> getList(ReviewPageRequestDTO reviewPageRequestDTO){
        try {
            Sort sort = Sort.by("rno").ascending();
            Pageable pageable = reviewPageRequestDTO.getPageable(sort);
            return reviewRepository.list(reviewPageRequestDTO.getPno(), pageable);
        }catch (Exception e) {
            log.error("--- " + e.getMessage());
            throw ProductException.NOT_FETCHED.get();
        }
    }
}
