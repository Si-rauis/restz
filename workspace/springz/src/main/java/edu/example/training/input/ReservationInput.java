package edu.example.training.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.time.LocalDateTime;

//DTO - Data Transfer Object
@Data
public class ReservationInput {


    private String trainingId;                  //과목 ID

    @NotBlank
    private String studentTypeCode;             //학생 타입

    @NotBlank
    private String name;                        //예약자 이름

    @NotBlank
    @Pattern(regexp = "0\\d{1,4}-\\d{1,4}-\\d{4}")
    private String phone;                       //예약자 휴대번호

    @NotBlank
    @Email
    private String emailAddress;                //예약자 이메일

}
