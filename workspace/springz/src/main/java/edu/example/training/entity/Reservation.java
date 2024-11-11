package edu.example.training.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reservation {
    private String id;                          //예약 번호
    private String trainingId;                  //과목 ID
    private String student_type_id;             //학생 타입
    private LocalDateTime reservedDateTime;     //예약 시간
    private String name;                        //예약자 이름
    private String phone;                       //예약자 휴대번호
    private String emailAddress;                //예약자 이메일

    private Training training;
    private StudentType studentType;

}
