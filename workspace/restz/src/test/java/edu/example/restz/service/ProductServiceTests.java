package edu.example.restz.service;

import edu.example.restz.dto.PageRequestDTO;
import edu.example.restz.dto.ProductDTO;
import edu.example.restz.dto.TodoDTO;
import edu.example.restz.exception.EntityNotFoundException;
import edu.example.restz.exception.ProductException;
import edu.example.restz.sevice.ProductService;
import edu.example.restz.sevice.TodoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Log4j2
public class ProductServiceTests {
    @Autowired                       //1. 필드 인젝션 어노ㅌㅔ이션
    private ProductService productService; //2. 객체 선언

    @Test                           //3. Junit 테스트를 위한 어노테이션
    public void testRegister(){
        ProductDTO productDTO = new ProductDTO();     //4. ProductDTO 객체를 생성하여
        String img1 = "new1.jpg";
        String img2 = "new2.jpg";
        List<String> imgs = Arrays.asList(img1, img2);

        productDTO.setPno(51L);             //   임의의 데이터를 저장한 후
        productDTO.setPname("신규 상품_51");
        productDTO.setPrice(5000);
        productDTO.setDescription("상품 설명");
        productDTO.setRegisterId("user6");
        productDTO.setImages(imgs);

        ProductDTO savedProduct = productService.register(productDTO);      //5. 데이터베이스에 저장하는 메서드를 호출하고 반환되는 값을 저장
        assertNotNull(savedProduct);                                        //6. 반환된 결과가 null이 아닌지 검증
        assertEquals("신규 상품_51", savedProduct.getPname());       //7. 반환된 결과의 Pname가 4에서 지정한 값과 같은지 검증
        log.info(savedProduct);                                             //8. 반환된 객체를 info 레벨의 로그로 출력
    }

    @Test
    public void testRead(){
        Long pno = 50L;                            //given
        ProductDTO productDTO = productService.read(pno);    //when
        assertNotNull(productDTO);                     //then
        assertEquals(pno, productDTO.getPno());

        log.info(productDTO);
    }

//    @Test
//    public void testRemove(){
//        try {
//            Long mno = 4L;              //given - 4번 글
//            todoService.remove(mno);    //when  - 4번 글 삭제
//        } catch(EntityNotFoundException e ) {
//            log.info("EntityNotFoundException message " + e.getMessage());
//            //then  - 4번 최초 삭제 시에는 예외 X
//            //        삭제 된 이후 재시도 시 예외코드가 404와 같은지 확인
//            assertEquals(404, e.getCode());
//        }
//    }
//
//    @Test
//    public void testModify(){
//        TodoDTO todoDTO = new TodoDTO();
//        todoDTO.setMno(5L);
//        todoDTO.setTitle("SERVICE MODIFIED");
//        todoDTO.setWriter("modifier");
//        todoDTO.setDueDate(LocalDate.of(2024, 11, 30));
//
//        TodoDTO modifyTodo = todoService.modify(todoDTO);
//        assertNotNull(modifyTodo);
//        assertEquals("SERVICE MODIFIED", modifyTodo.getTitle());
//        assertEquals("modifier", modifyTodo.getWriter());
//        log.info(modifyTodo);
//    }
//
//    @Test
//    public void testGetList(){
//        PageRequestDTO pageRequestDTO = new PageRequestDTO(); //기본생성자 - 1 페이지, 게시물 10개씩
//
//        Page<TodoDTO> todoPage = todoService.getList(pageRequestDTO);
//        assertNotNull( todoPage );
//        assertEquals(100, todoPage.getTotalElements()); //전체 게시물 수 100개
//        assertEquals(10, todoPage.getTotalPages());     //총 페이지 수 10개
//        assertEquals(0,  todoPage.getNumber()) ;        //현재 페이지 번호 0
//        assertEquals(10, todoPage.getSize());           //한 페이지 게시물 수 10
//        assertEquals(10, todoPage.getContent().size()); //      "
//
//        todoPage.getContent().forEach(System.out::println);
//    }
}
