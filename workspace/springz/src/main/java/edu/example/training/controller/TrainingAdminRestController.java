package edu.example.training.controller;

import edu.example.training.entity.Training;
import edu.example.training.exception.CapacityOverException;
import edu.example.training.exception.DataNotFoundException;
import edu.example.training.input.TrainingInput;
import edu.example.training.service.TrainingAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.List;

@RestController("/trainings")
@RequiredArgsConstructor
public class TrainingAdminRestController {
    private final TrainingAdminService trainingAdminService;

    @DeleteMapping("api/trainings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)      //상태 코드 204 - 정상 처리, 응답바디는 없음
    public void removeTraining(@PathVariable String id){
        trainingAdminService.delete(id);
    }

    @GetMapping("/api/test/{id}")   //트래이닝 정보 하나 조회
    public Training test(@PathVariable String id){
        Training training = null;
        if(training == null){
            throw new DataNotFoundException("데이터를 찾을 수 없습니다. id : " + id);
        }
        return training;
    }

    //데이터가 존재하지 않는 예외 발생 시 - 지정된 메세지 반환
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(){
        return "예외 발생 : 데이터 X";
    }

    @PostMapping("/api/trainings")     //트래이닝 정보 추가
    public ResponseEntity <Void> addTraining(
            @Validated @RequestBody TrainingInput trainingInput){
        trainingAdminService.add(trainingInput);
        //신규 추가된 리소스 식별을 위한 URL
        URI url = ServletUriComponentsBuilder
                .fromCurrentRequestUri()                    // /api/training
                .path("/{id}")                              // /api/training/{id}
                .buildAndExpand(trainingInput.getId())      // /api/training/ 신규id
                .toUri();
        return ResponseEntity.created(url).build();
    }


    @PutMapping("/api/trainings/{id}")     //트래이닝 정보 수정
    @ResponseStatus(HttpStatus.NO_CONTENT)      //상태 코드 204 - 정상 처리, 응답바디는 없음
    public void modifyTraining(@PathVariable String id,
                               @Validated @RequestBody TrainingInput trainingInput){
        trainingInput.setId(id);
        trainingAdminService.modify(trainingInput);
    }

    @GetMapping("/api/trainings")        //트래이닝 목록 조회
    public List<Training> getTrainings(){
        List<Training> list = trainingAdminService.findByTraining();
        return list;
    }

    @GetMapping("/api/trainings/{id}")   //트래이닝 정보 하나 조회
    public Training getTraining(@PathVariable String id){
        return trainingAdminService.findById(id);
    }
}
