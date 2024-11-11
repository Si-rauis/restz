package edu.example.training.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrainingInput {

    private String id;

    @NotBlank
    private String title;

    @NotNull
    private LocalDateTime startDateTime;

    @NotNull
    private LocalDateTime endDateTime;

    @NotNull
    @Min(0)
    private Integer reserved;

    @NotNull
    @Min(1)
    private Integer capacity;


}
