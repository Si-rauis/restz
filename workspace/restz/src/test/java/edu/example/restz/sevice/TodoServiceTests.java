package edu.example.restz.sevice;


import edu.example.restz.dto.PageRequestDTO;
import edu.example.restz.dto.TodoDTO;
import edu.example.restz.entity.Todo;
import edu.example.restz.exception.EntityNotFoundException;
import edu.example.restz.repository.TodoRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Log4j2
public class TodoServiceTests {
    //1. 필드 인젝션 어노테이션
    @Autowired
    //2. 객체 선언
    private TodoService todoService;
    @Autowired
    private TodoRepository todoRepository;

    //3. Junit 테스트를 위한 어노테이션
    @Test
    public void testRegister(){
        //4.TodoDTO 객체를 생성하여
        //  임의의 데이터를 저장한 후
        TodoDTO todoDTO = new TodoDTO(new Todo(102L,"new Register", "Register", LocalDate.now()));


        //5. 데이터베이스에 저장하는 메서드 호출하고 반환되는 값을 저장
        TodoDTO todoTestDTO = todoService.register(todoDTO);
        //6. 저장된 결과가 null이 아닌지 검증
        assertNotNull(todoTestDTO);
        //7. 반환된 결과가 writer가 4에서 지정한 값과 같은지 검증
        assertEquals(todoDTO.getWriter(), todoTestDTO.getWriter());
        //8.반환된 객체를 info 레벨의 로그로 출력
        log.info("todoDTO : " + todoTestDTO);
    }

    @Test
    public void testFind(){
        Long mno = 222L;                                  //GIVEN

        TodoDTO todoDTO = todoService.read(mno);        //WHEN

        assertNotNull(todoDTO);                         //THEN
        assertEquals(1, todoDTO.getMno());

        log.info("todoDTO : " + todoDTO);
        log.info("mno : " + todoDTO.getMno());
    }

    @Test
    public void testRemove(){
        try {
            Long mno = 4L;                                  //GIVEN -4번 글

            todoService.remove(mno);                        //WHEN  -4번 글 삭제
        } catch (EntityNotFoundException e) {
            log.info("EntityNotFoundException : " + e.getMessage());
            //THEN  -4번 최초 삭제 시에는 예외 X
            //      -4번이 삭제 된 이후 재시도 시 예외코드가 404와 같은지 확인
            assertEquals(404, e.getCode());
        }
    }

    @Test
    public void testModify(){
        TodoDTO todoDTO = new TodoDTO(new Todo(5L,"SERVICE Modify", "Modify", LocalDate.now()));
        try {
            TodoDTO todoTestDTO = todoService.modify(todoDTO);

            assertNotNull(todoTestDTO);
            assertEquals(todoDTO.getWriter(), todoTestDTO.getWriter());
            assertEquals(todoDTO.getTitle(), todoTestDTO.getTitle());

            log.info("todoDTO : " + todoTestDTO);

        } catch (EntityNotFoundException e) {
            log.info("EntityNotFoundException : " + e.getMessage());
        }
    }

    @Test   //DTO projections 테스트
    public void testGetList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();   //기본 생성자 -> 1 페이지, 게시물 10개씩

        Page<TodoDTO> todoPage = todoService.getList(pageRequestDTO);

        assertNotNull(todoPage);
        assertEquals(99,todoPage.getTotalElements());       //전체 게시물 수 100개
        assertEquals(10, todoPage.getTotalPages());         //총 페이지 수 10개
        assertEquals(0, todoPage.getNumber());              //현재 페이지 번호 1
        assertEquals(10, todoPage.getSize());               //한 페이지 게시물 수 10
        assertEquals(10, todoPage.getContent().size());     //        "

        todoPage.getContent().forEach(System.out::println);
    }
}
