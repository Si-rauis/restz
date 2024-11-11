package edu.example.restz.sevice;

import edu.example.restz.dto.CartDTO;
import edu.example.restz.dto.ProductDTO;
import edu.example.restz.dto.ReviewDTO;
import edu.example.restz.entity.Cart;
import edu.example.restz.entity.CartItem;
import edu.example.restz.entity.Product;
import edu.example.restz.entity.Review;
import edu.example.restz.exception.CartException;
import edu.example.restz.exception.ProductException;
import edu.example.restz.exception.ReviewException;
import edu.example.restz.repository.CartItemRepository;
import edu.example.restz.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service                    //1. 빈 등록
@RequiredArgsConstructor    //2. 생성자
@Transactional              //3. 트랜잭션
@Log4j2
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    //등록
    public CartDTO register(CartDTO cartDTO, String mid) {
        Cart cart = cartRepository.getCart(mid)
                                  .orElseGet(() -> {
                                              Cart newCart = Cart.builder()
                                                        .CartNum(0)
                                                        .customer(mid)
                                                        .build();
                                              return cartRepository.save(newCart); // 먼저 저장
                                          }
                                       );
        try {
            CartItem cartItem = cartDTO.toEntity();
            cartItem.changeCart(cart);
            cartItemRepository.save(cartItem);
            //상품 등록시 예외가 발생한 경우
            cart.changeCartNum(cart.getCartNum() + 1);
            return new CartDTO(cartItem);
        }catch (DataIntegrityViolationException e) {
            throw CartException.PRODUCT_NOT_FOUND.get();
        }
        catch (Exception e) {
            log.error("--- " + e.getMessage());
            throw CartException.FAIL_ADD.get();
        }
    }

    //조회
    public List<CartDTO> read(Long cno){
        Optional<List<CartDTO>> cartDTO = cartItemRepository.getCartDTO(cno);
        cartDTO.orElseThrow(CartException.NOT_FOUND_CART::get);

        List<CartDTO> cartDTOList = cartDTO.get();
        return cartDTOList;
    }

    //수정
    public CartDTO modify(CartDTO cartDTO, String mid) {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartDTO.getItemNo());
        CartItem modifiedCartItem = cartItem.orElseThrow(CartException.NOT_FOUND_CARTITEM::get);
        Cart modifiedCart = modifiedCartItem.getCart();

        if (!cartItem.get().getCart().getCustomer().equals(mid)) {
            throw CartException.NOT_MATCHED_CUSTOMER.get();
        }

        //필요한 부분 수정 - 변경이 감지되면 수정 처리 수행
        try {
            //상품 이름, 가격, 설명 수정
            modifiedCartItem.changeQuantity(cartDTO.getQuantity());
            modifiedCart.updateModDate();

            return new CartDTO(modifiedCartItem);
        } catch (Exception e) {
            throw CartException.FAIL_MODIFIED.get();
        }
    }

    //삭제
    public void remove (Long itemNo, String mid){
            //1. itemNo 해당하는 데이터를 조회한 결과 저장
        Optional<CartItem> foundCartItem = cartItemRepository.findById(itemNo);
            //2. 1의 결과가 없으면 NOT_FOUND 발생 시키기
        CartItem cartItem = foundCartItem.orElseThrow(CartException.NOT_MATCHED_ItemNO::get);
        Cart removedCart = cartItem.getCart();
        int updateCartNum = removedCart.getCartNum() - 1;

        if (!cartItem.getCart().getCustomer().equals(mid)) {
            throw CartException.NOT_MATCHED_CUSTOMER.get();
        }

        try {
            //3. itemNo 해당하는 데이터 삭제 메서드 호출
            cartItemRepository.deleteById(itemNo);
            removedCart.changeCartNum(updateCartNum);
            if(updateCartNum == 0){
                cartRepository.deleteById(removedCart.getCno());
            }
        } catch (Exception e) {
            log.error("--- " + e.getMessage());
            throw CartException.FAIL_REMOVED.get();
        }
    }
}
