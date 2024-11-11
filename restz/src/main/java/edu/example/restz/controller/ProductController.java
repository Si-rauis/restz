package edu.example.restz.controller;


import edu.example.restz.dto.PageRequestDTO;
import edu.example.restz.dto.ProductDTO;
import edu.example.restz.exception.ProductException;
import edu.example.restz.sevice.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Log4j2
public class ProductController {
    private final ProductService productService;

    //등록
    @PostMapping("")
    public ResponseEntity<ProductDTO> register(
            @Validated @RequestBody ProductDTO productDTO,
                                    Principal principal){
        log.info("--- register()");
        log.info("--- productDTO : " + productDTO);         //로그로 출력

        //이미지가 없는 경우 NO Product Image를 예외 메시지로 ProductTaskException 예외 발생 시키기
        if(productDTO.getImages().isEmpty() || productDTO.getImages() == null){
            throw ProductException.NOT_IMAGE.get();
        }
        //인증된 사용자와 productDTO의 등록자가 일치하지 않는 경우
        if(!principal.getName().equals(productDTO.getRegisterId())){
            //No Authenticated user를 예외 메시지로 ProductTaskException 예외 발생 시키기
            throw ProductException.NOT_REGISTERED.get();
        }
        //상태 코드를 200 OK로 하여, 상품 등록 서비스가 반환하는 데이터를 뷰로 전달
        return ResponseEntity.ok(productService.register(productDTO));
    }

    //조회
    @GetMapping("/{pno}")  //GET 요청으로 Product 번호를 넘겨받아 조회
    public ResponseEntity<ProductDTO> read(@PathVariable("pno") Long pno) {
        log.info("pno : " + pno);
        return ResponseEntity.ok(productService.read(pno));
    }

    //수정
    @PutMapping("/{pno}")
    public ResponseEntity<ProductDTO> modify(@Validated @RequestBody ProductDTO productDTO,
                                             Authentication authentication,
                                             @PathVariable("pno") Long pno) {
        log.info("--- modify()");
        log.info("--- ProductDTO : " + productDTO);
//        log.info("--- Principal : " + principal);
        log.info("--- Authentication : " + authentication);

        if( !pno.equals(productDTO.getPno()) ){
            //pno가 일치하지 않는 경우
            throw ProductException.NOT_FOUND.get();
        }

        if(productDTO.getImages().isEmpty() || productDTO.getImages() == null){
            throw ProductException.NOT_IMAGE.get();
        }

        if(!authentication.getName().equals(productDTO.getRegisterId())){
            throw ProductException.NOT_REGISTERED.get();
        }

        return ResponseEntity.ok(productService.modify(productDTO));
    }

    //삭제
    @DeleteMapping("/{pno}")
    public ResponseEntity<Map<String, String>> remove(@PathVariable("pno") Long pno,
                                                      Authentication authentication) {
        log.info("remove() : " + pno);

        String mid = productService.read(pno).getRegisterId();

        if(!authentication.getName().equals(mid)){  // 등록한 사용자가 아닌 경우
            Collection<? extends GrantedAuthority> authorities
                    = authentication.getAuthorities();  //ADMIN role이 없으면 예외 발생

            authorities.stream()
                       .filter(authority -> authority.getAuthority().equals("ROLE_ADMIN"))
                       .findAny().orElseThrow(() -> ProductException.REGISTER_ERR.get());
        }

        productService.remove(pno, authentication.getName());        //삭제 처리 후
        return ResponseEntity.ok(Map.of("result", "success"));
    }

    //목록
    @GetMapping("")
    public ResponseEntity<Page<ProductDTO>> getList(@Validated PageRequestDTO pageRequestDTO,
                                                    Authentication authentication){
        log.info("getList() ----- " + pageRequestDTO);         //로그로 출력

        //기본 요청     - 1page 10개
        //2page 요청   - 2page 10개
        //-2page 요청  - page : "1 이상이어야 합니다."
        //2page size 5 요청 - size : "10 이상이어야 합니다."
        //Apage 요청 - page : "Failed to convert property value of type 'java.lang.String'...

        return ResponseEntity.ok(productService.getList(pageRequestDTO));
    }
}
