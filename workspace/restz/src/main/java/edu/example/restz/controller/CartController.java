package edu.example.restz.controller;

import edu.example.restz.dto.CartDTO;
import edu.example.restz.dto.ProductDTO;
import edu.example.restz.exception.CartException;
import edu.example.restz.exception.ProductException;
import edu.example.restz.sevice.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
@Log4j2
public class CartController {
    protected final CartService cartService;

    //장바구니 담기
    @PostMapping()
    @PreAuthorize("authentication.name == #cartDTO.customer")
    public ResponseEntity<CartDTO> addCart(@Validated @RequestBody CartDTO cartDTO,
                                           Authentication authentication) {
        log.info("--- addCart()");
        log.info("--- CartDTO : " + cartDTO);         //로그로 출력

        return ResponseEntity.ok(cartService.register(cartDTO, authentication.getName()));
    }

    //장바구니 조회
    @GetMapping("/{cno}")
    public ResponseEntity<List<CartDTO>> getCart(@PathVariable("cno") Long cno) {
        log.info("--- getCart()");

        List<CartDTO> list = cartService.read(cno);
        return ResponseEntity.ok(list);
    }

    //장바구니 수정
    @PutMapping("/{itemNo}")
    public ResponseEntity<CartDTO> modifyCart(@Validated @RequestBody CartDTO cartDTO,
                                              Authentication authentication,
                                              @PathVariable("itemNo") Long itemNo){
        log.info("--- modifyCart()");
        log.info("--- CartDTO : " + cartDTO);
        log.info("--- ItemNo : " + itemNo);

        if( !itemNo.equals(cartDTO.getItemNo()) ){
            //itemNo 일치하지 않는 경우
            throw CartException.NOT_MATCHED_ItemNO.get();
        }

        return ResponseEntity.ok(cartService.modify(cartDTO, authentication.getName()));
    }



    //장바구니 삭제
    @DeleteMapping("/{itemNo}")
    public ResponseEntity<Map<String, String>> removeCart(Authentication authentication,
                                                          @PathVariable("itemNo") Long itemNo){
        log.info("--- modifyCart()");
        log.info("--- ItemNo : " + itemNo);

        cartService.remove(itemNo,authentication.getName());

        return ResponseEntity.ok(Map.of("result", "success"));
    }

}
