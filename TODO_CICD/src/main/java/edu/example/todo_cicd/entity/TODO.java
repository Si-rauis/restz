package edu.example.todo_cicd.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "todo")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TODO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int todoId;

    private String todoTitle;

    private String todoDescription;

    @CreatedDate
    private LocalDateTime createDate;
}
