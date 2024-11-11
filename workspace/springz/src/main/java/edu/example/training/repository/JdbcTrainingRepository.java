package edu.example.training.repository;


import edu.example.training.entity.Training;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
//@Profile("production")
public class JdbcTrainingRepository implements TrainingRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTrainingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public Training selectById(String id) {
        Training result = jdbcTemplate.queryForObject(
               " SELECT * FROM traning WHERE id = ?",
                new DataClassRowMapper<>(Training.class), id);
        return result;
    }


    @Override
    public boolean update(Training training) {
        int result = jdbcTemplate.update(
                " UPDATE traning SET title = ?, start_date_time = ?, end_date_time = ?, reserved = ?, capacity = ? WHERE id = ?",
                training.getTitle(),
                training.getStartDateTime(),
                training.getEndDateTime(),
                training.getReserved(),
                training.getCapacity(),
                training.getId());
        return result == 1;
    }

    @Override
    public boolean insert(Training training) {
        int result = jdbcTemplate.update(
                " INSERT INTO traning VALUES (?,?,?,?,?,?)",
                training.getId(),
                training.getTitle(),
                training.getStartDateTime(),
                training.getEndDateTime(),
                training.getReserved(),
                training.getCapacity());
        return result == 1;
    }

    @Override
    public boolean delete(String id) {
        int result = jdbcTemplate.update(
                " DELETE FROM traning WHERE id = ?",
                id);
        return result == 1;
    }

    @Override
    public List<Training> selectTrainingMapList() {
        List<Training> result = jdbcTemplate.query(
                " SELECT * FROM traning",
                new DataClassRowMapper<>(Training.class));
        return result;
    }
}
