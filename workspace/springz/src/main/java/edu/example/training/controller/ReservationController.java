package edu.example.training.controller;

import edu.example.training.entity.Reservation;
import edu.example.training.entity.StudentType;
import edu.example.training.entity.Training;
import edu.example.training.exception.CapacityOverException;
import edu.example.training.input.ReservationInput;
import edu.example.training.service.ReservationService;
import edu.example.training.service.TrainingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final TrainingService trainingService;

    public ReservationController(ReservationService reservationService,
                                 TrainingService trainingService) {
        this.reservationService = reservationService;
        this.trainingService = trainingService;
    }

    //신청 인원 초과 예외 발생 시 - 지정된 예외화면으로 이동
    @ExceptionHandler(CapacityOverException.class)
    public String displayCapacityOverPage() {
        return "reservation/capacityOver";
    }

    //신청 확인
    @PostMapping(value = "/reserve", params = "reserve")
    public String reserve(@Validated ReservationInput reservationInput,
                          Model model){
        //reservation 테이블에 추가하고 //training 테이블에 reserved를 1 증가시키고
        Reservation reservation = reservationService.reserve(reservationInput);
        //예약 완료된 reservation 객체를 가지고 뷰로 이동
        model.addAttribute(reservation);
        return "reservation/reservationCompleted";
    }

    //신청 변경
    @PostMapping(value = "/reserve", params = "correct")
    public String correctInput(@Validated ReservationInput reservationInput,
                               Model model){
            List<StudentType> studentType = reservationService.findAllStudentType();
            model.addAttribute("studentTypeList", studentType);
            return "/reservation/reservationForm"; //원래 입력화면으로 돌아가기
    }



    @PostMapping("/validate-input") //입력 내용 검증-------------------
    public String validateInput(@Validated ReservationInput reservationInput,
                                BindingResult bindingResult,
                                Model model) {
        System.out.println("/reservation/validate-input");

        if(bindingResult.hasErrors()){//입력 오류가 있으면
            List<StudentType> studentType = reservationService.findAllStudentType();
            model.addAttribute("studentTypeList", studentType);
            return "/reservation/reservationForm"; //원래 입력화면으로 돌아가기
        }

        //입력 오류가 없으면
        Training training = trainingService.findById(reservationInput.getTrainingId());
            //해당 트래이닝 정보를 가지고
        model.addAttribute("training", training);
            //확인화면으로 이동하기

        return "reservation/reservationConfirm";
    }

    @GetMapping("/display-form")    //신청화면 표시
    public String displayForm(@RequestParam String trainingId, Model model){
        System.out.println("/reservation/display-form");

        //입력값 유효성 검증 시에 사용자 선택 항목을 저장
        ReservationInput reservationInput = new ReservationInput();
        reservationInput.setTrainingId(trainingId);
        model.addAttribute("reservationInput", reservationInput);

        List<StudentType> studentType = reservationService.findAllStudentType();
        model.addAttribute("studentTypeList", studentType);

        return "/reservation/reservationForm";
    }

    @GetMapping("/display-details") //예약 상세 조회
    @ResponseBody
    public Reservation displayDetails(@RequestParam String reservationId){
        return reservationService.findById(reservationId);
    }

    @GetMapping("/training-details") //트래이닝별 예약 상세 조회
    @ResponseBody
    public Training trainingDetails(@RequestParam String trainingId){
        return reservationService.findTrainingById(trainingId);
    }
}
