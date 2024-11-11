package edu.example.training.repository;

import edu.example.training.entity.Reservation;
import edu.example.training.entity.StudentType;
import edu.example.training.entity.Training;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
//@Profile("production")
public class JdbcReservationRepository implements ReservationRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcReservationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    @Override
    public boolean insert(Reservation reservation) {
        int result = jdbcTemplate.update(
                " INSERT INTO reservation VALUES (?, ?, ?, NOW(), ?, ?, ?)",
                reservation.getId(),
                reservation.getTrainingId(),
                reservation.getStudent_type_id(),
                reservation.getName(),
                reservation.getPhone(),
                reservation.getEmailAddress());
        return result == 1;
    }

    @Override
    public Reservation selectById(String id) {
        String query = "SELECT re.id AS re_id, student_type_id, re.name AS re_name, st.id AS st_id, st.name AS st_name, st.code FROM reservation AS re LEFT OUTER JOIN student_type AS st ON re.student_type_id = st.id WHERE re.id = ?";
        Reservation result = jdbcTemplate.queryForObject(query,
                new ReservationRowMapper(), id);
        return result;
    }

    @Override
    public Training selectByTrainingId(String id) {
        String query = "SELECT r.id AS r_id, training_id, name,t.id AS t_id, title, start_date_time FROM traning AS t LEFT OUTER JOIN reservation AS r ON t.id = r.training_id WHERE t.id = ?";
        Training result = jdbcTemplate.query(query,
                new TrainingResultSetExtractor(), id);
        return result;
    }
    static class TrainingResultSetExtractor implements ResultSetExtractor<Training> {
        @Override
        public Training extractData(ResultSet rs) throws SQLException, DataAccessException {
            Training training = null;
            while (rs.next()) {
                if(training == null){
                    training = new Training();
                    training.setId(rs.getString("t_id"));
                    training.setTitle(rs.getString("title"));
                    training.setStartDateTime(rs.getTimestamp("start_date_time").toLocalDateTime());
                    training.setReservation(new ArrayList<>());
                }
                Reservation reservation = new Reservation();        //예약 정보
                reservation.setId(rs.getString("r_id"));
                reservation.setName(rs.getString("name"));

                training.getReservation().add(reservation);         //예약 목록에 저장
            }
            return training;
        }
    }


    static class ReservationRowMapper implements RowMapper<Reservation> {
        @Override
        public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Reservation reservation = new Reservation();
            reservation.setId(rs.getString("re_id"));
            reservation.setStudent_type_id(rs.getString("student_type_id"));
            reservation.setName(rs.getString("re_name"));

            StudentType studentType = new StudentType();
            studentType.setId(rs.getString("st_id"));
            studentType.setCode(rs.getString("code"));
            studentType.setName(rs.getString("st_name"));

            reservation.setStudentType(studentType);
            return reservation;
        }
    }
}
