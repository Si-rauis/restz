package edu.example.training.repository;

import edu.example.training.entity.StudentType;
import edu.example.training.entity.Training;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Profile("production")
public class JdbcStudentTypeRepository implements StudentTypeRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcStudentTypeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public StudentType selectByCode(String studentTypeCode) {
        StudentType result = jdbcTemplate.queryForObject(
                " SELECT * FROM student_type WHERE code = ?",
                new DataClassRowMapper<>(StudentType.class), studentTypeCode);
        return result;
    }

    @Override
    public List<StudentType> selectAll() {
        List<StudentType> result = jdbcTemplate.query(
                " SELECT * FROM student_type",
                new DataClassRowMapper<>(StudentType.class));
        return result;
    }

//    @Override
//    public boolean update(Training training) {
//        int result = jdbcTemplate.update(
//                " UPDATE traning SET title = ?, Capacity = ? WHERE id = ?",
//                training.getTitle(),
//                training.getCapacity(),
//                training.getId());
//        return result == 1;
//    }
}
