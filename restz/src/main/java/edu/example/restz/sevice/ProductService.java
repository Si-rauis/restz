package edu.example.restz.sevice;

import edu.example.restz.dto.PageRequestDTO;
import edu.example.restz.dto.ProductDTO;
import edu.example.restz.dto.TodoDTO;
import edu.example.restz.entity.Product;
import edu.example.restz.entity.Todo;
import edu.example.restz.exception.EntityNotFoundException;
import edu.example.restz.exception.ProductException;
import edu.example.restz.exception.ProductTaskException;
import edu.example.restz.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service                    //1. 빈 등록
@RequiredArgsConstructor    //2. 생성자
@Transactional              //3. 트랜잭션
@Log4j2
public class ProductService {
    public final ProductRepository productRepository;

    //상품 등록
    public ProductDTO register(ProductDTO productDTO) {
        try {
            Product product = productDTO.toEntity();
            productRepository.save(product);
            //상품 등록시 예외가 발생한 경우
            return new ProductDTO(product);
        }catch (Exception e) {
            //예외 메시지를 Product NOT Registered로 지정하여
            //ProductTaskException 발생시키기
            log.error("--- " + e.getMessage());
            throw ProductException.NOT_REGISTERED.get();
        }
    }

    //상품 조회
    public ProductDTO read(Long pno){
        Optional<ProductDTO> productDTO = productRepository.getProductDTO(pno);
        return productDTO.orElseThrow(ProductException.NOT_FOUND::get);   //값이 없으면 예외 발생
    }

    //상품 삭제
    public void remove(Long pno, String mid) {
        //1. pno에 해당하는 데이터를 조회한 결과 저장
        Optional<Product> foundproduct = productRepository.findById(pno);
        //2. 1의 결과가 없으면 NOT_FOUND 발생 시키기
        Product product = foundproduct.orElseThrow(ProductException.NOT_FOUND::get);

        if(!product.getRegisterId().equals(mid)) {
            throw ProductException.REGISTER_ERR.get();
        }

        try {
            //3. pno에 해당하는 데이터 삭제 메서드 호출
            productRepository.deleteById(pno);
        } catch (Exception e) {
            log.error("--- " + e.getMessage());
            throw ProductException.NOT_REMOVED.get();
        }
    }

    //상품 수정
    public ProductDTO modify(ProductDTO productDTO) {
        //1. pno에 해당하는 데이터를 조회한 결과 저장
        Optional<Product> product = productRepository.findById(productDTO.getPno());

        //2. 1의 결과가 없으면 EntityNotFoundException 발생 시키기
        Product modifiedProduct = product.orElseThrow(ProductException.NOT_FOUND::get);

        if(productDTO.getPno() != modifiedProduct.getPno()) {
            throw ProductException.REGISTER_ERR.get();
        }

        //필요한 부분 수정 - 변경이 감지되면 수정 처리 수행
        try {
            //상품 이름, 가격, 설명 수정
            modifiedProduct.changePname(productDTO.getPname());
            modifiedProduct.changeDescription(productDTO.getDescription());
            modifiedProduct.changePrice(productDTO.getPrice());
            //기존 이미지 삭제
            modifiedProduct.clearImages();
            //새 이미지 목록을 가져와서 추가
            List<String> filenames = productDTO.getImages();

            if(filenames != null && !filenames.isEmpty()) {
                filenames.forEach(modifiedProduct::addImage);
            }
            return new ProductDTO(modifiedProduct);
        } catch (Exception e) {
            throw ProductException.NOT_MODIFIED.get();
        }
    }

    //상품 목록
    public Page<ProductDTO> getList(PageRequestDTO pageRequestDTO){
        try {
            Sort sort = Sort.by("pno").descending();
            Pageable pageable = pageRequestDTO.getPageable(sort);
            return productRepository.getProductDTOQuery(pageable);
        }catch (Exception e) {
            log.error("--- " + e.getMessage());
            throw ProductException.NOT_FETCHED.get();
        }
    }
}
