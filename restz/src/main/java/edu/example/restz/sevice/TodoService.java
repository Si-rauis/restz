package edu.example.restz.sevice;

import edu.example.restz.dto.PageRequestDTO;
import edu.example.restz.dto.TodoDTO;
import edu.example.restz.entity.Todo;
import edu.example.restz.exception.EntityNotFoundException;
import edu.example.restz.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service                    //1. 빈 등록
@RequiredArgsConstructor    //2. 생성자
@Transactional              //3. 트랜잭션
@Log4j2
public class TodoService {
    private final TodoRepository todoRepository;        //4.의존성 주입

    //조회
    public TodoDTO read(Long mno){
        Optional<TodoDTO> todoDTO = todoRepository.getTodoDTO(mno);
        return todoDTO.orElseThrow(() ->
                new EntityNotFoundException("Todo " + mno + " NOT FOUND"));   //값이 없으면 예외 발생
    }

    //등록
    public TodoDTO register(TodoDTO todoDTO) {
        Todo todo = todoDTO.toEntity();
        todoRepository.save(todo);
        return new TodoDTO(todo);
    }

    //삭제
    public void remove(Long mno) {
        //1. mno에 해당하는 데이터를 조회한 결과 저장
        Optional<Todo> todo = todoRepository.findById(mno);
        //2. 1의 결과가 없으면 EntityNotFoundException 발생 시키기
        Todo removeTodo = todo.orElseThrow(() ->
                new EntityNotFoundException("Todo " + mno + " NOT FOUND"));   //값이 없으면 예외 발생
        //3. mno에 해당하는 데이터 삭제 메서드 호출
        todoRepository.deleteById(mno);
    }

    //수정
    public TodoDTO modify(TodoDTO todoDTO) {
        //1. mno에 해당하는 데이터를 조회한 결과 저장
        Optional<Todo> todo = todoRepository.findById(todoDTO.getMno());
        //2. 1의 결과가 없으면 EntityNotFoundException 발생 시키기
        Todo modifyTodo = todo.orElseThrow(() ->
                new EntityNotFoundException("Todo " + todoDTO.getMno() + " NOT FOUND"));   //값이 없으면 예외 발생

        //필요한 부분 수정 - 변경이 감지되면 수정 처리 수행
        modifyTodo.changeWriter(todoDTO.getWriter());
        modifyTodo.changeTitle(todoDTO.getTitle());
        modifyTodo.changeDueDate(todoDTO.getDueDate());

        return new TodoDTO(modifyTodo);
    }

    //목록
    public Page<TodoDTO> getList(PageRequestDTO pageRequestDTO){
        Sort sort = Sort.by("mno").descending();
        Pageable pageable = pageRequestDTO.getPageable(sort);

        return todoRepository.searchDTO(pageable);
    }
}
