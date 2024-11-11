package edu.example.training.repository;

import edu.example.training.entity.StudentType;
import java.util.List;

public interface StudentTypeRepository {
    StudentType selectByCode(String studentTypeCode);

    List<StudentType> selectAll();
}
