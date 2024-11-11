package edu.example.restz.controller;

import edu.example.restz.dto.PageRequestDTO;
import edu.example.restz.dto.ReviewDTO;
import edu.example.restz.dto.ReviewPageRequestDTO;
import edu.example.restz.exception.ReviewException;
import edu.example.restz.sevice.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
@Log4j2
public class ReviewController {
    private final ReviewService reviewService;

    //등록
    @PostMapping()
    public ResponseEntity<ReviewDTO> register(@Validated @RequestBody ReviewDTO reviewDTO,
                                              Principal principal) {
        log.info("--- register()");
        log.info("--- ReviewDTO : " + reviewDTO);         //로그로 출력

        //인증된 사용자와 ReviewDTO의 등록자가 일치하지 않는 경우
        if(!principal.getName().equals(reviewDTO.getReviewer())){
            //No Authenticated user를 예외 메시지로 ProductTaskException 예외 발생 시키기
            throw ReviewException.NOT_MATCHED_REVIEWER.get();
        }
        //상태 코드를 200 OK로 하여, 상품 등록 서비스가 반환하는 데이터를 뷰로 전달
        return ResponseEntity.ok(reviewService.register(reviewDTO));
    }

    //조회
    @GetMapping("/{rno}")
    public ResponseEntity<ReviewDTO> read(@PathVariable("rno") Long rno) {
        log.info("--- read()");
        return ResponseEntity.ok(reviewService.read(rno));
    }

    //수정
    @PutMapping("/{rno}")
    public ResponseEntity<ReviewDTO> modify(@Validated @RequestBody ReviewDTO reviewDTO,
                                            Authentication authentication,
                                            @PathVariable("rno") Long rno){
        log.info("--- modify()");
        log.info("--- ReviewDTO : " + reviewDTO);
        log.info("--- Authentication : " + authentication);

        if( !rno.equals(reviewDTO.getRno()) ){
            //rno가 일치하지 않는 경우
            throw ReviewException.NOT_FOUND.get();
        }

        String reviewer = reviewService.read(rno).getReviewer();
        if(!authentication.getName().equals(reviewDTO.getReviewer()) || !reviewer.equals(reviewDTO.getReviewer())){
            throw ReviewException.NOT_MATCHED_REVIEWER.get();
        }

        return ResponseEntity.ok(reviewService.modify(reviewDTO));
    }

    //삭제
    @DeleteMapping("/{rno}")
    public ResponseEntity<Map<String, String>> remove(@PathVariable("rno") Long rno,
                                                      Authentication authentication) {
        log.info("remove() : " + rno);

        String reviewer = reviewService.read(rno).getReviewer();

        if(!authentication.getName().equals(reviewer)){  // 등록한 사용자가 아닌 경우
            Collection<? extends GrantedAuthority> authorities
                    = authentication.getAuthorities();  //ADMIN role이 없으면 예외 발생

            authorities.stream()
                    .filter(authority -> authority.getAuthority().equals("ROLE_ADMIN"))
                    .findAny().orElseThrow(() -> ReviewException.NOT_MATCHED_REVIEWER.get());
        }

        reviewService.remove(rno, authentication.getName());        //삭제 처리 후
        return ResponseEntity.ok(Map.of("result", "success"));
    }

    //목록
    @GetMapping("/list/{pno}")
    public ResponseEntity<Page<ReviewDTO>> getList(@PathVariable("pno") Long pno,
                                                   @Validated ReviewPageRequestDTO reviewPageRequestDTO,
                                                   Authentication authentication){
        log.info("getList() ----- " + reviewPageRequestDTO);         //로그로 출력

        reviewPageRequestDTO.setPno(pno);

        return ResponseEntity.ok(reviewService.getList(reviewPageRequestDTO));
    }
}
