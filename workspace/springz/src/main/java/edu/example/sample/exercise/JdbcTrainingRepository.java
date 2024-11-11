//package edu.example.sample;
//
//import edu.example.training.entity.Training;
//import org.springframework.jdbc.core.DataClassRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Map;
//
//@Repository
////@Profile("production")
//public class JdbcTrainingRepository implements TrainingRepository {
////  private final DataSource dataSource;
////  public JdbcTrainingRepository(DataSource dataSource) {
////      this.dataSource = dataSource;
////  }
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public JdbcTrainingRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//
//    @Override   //한 컬럼 데이터 받아오기 - 검색 파라미터 하나
//    public void selectTitle(String id) {
//        String result = jdbcTemplate.queryForObject(
//                " SELECT title FROM training WHERE id = ? ",
//                String.class, id);
//        System.out.println("id : " + id);
//        System.out.println("title : " + result);
//    }
//
//    @Override  //한 컬럼 데이터 받아오기 - 검색 파라미터 두 개
//    public void selectTitle(String id, String title) {
//        String result = jdbcTemplate.queryForObject(
//                " SELECT title FROM traning WHERE id = ? AND title = ? ",
//                String.class, id, title);
//        System.out.println("id : " + id);
//        System.out.println("title : " + result);
//    }
//
//    @Override   //날짜 데이터 받아오기
//    public void selectStart(String id) {
//        LocalDateTime result = jdbcTemplate.queryForObject(
//                " SELECT start_date_time FROM traning WHERE id = ?",
//                LocalDateTime.class, id);
//        System.out.println("id : " + id);
//        System.out.println("start_date_time : " + result);
//    }
//
//    @Override
//    public void selectReserveds() {
//        List<Integer> result = jdbcTemplate.queryForList(
//                " SELECT reserved FROM traning",
//                Integer.class);
//        result.forEach(System.out::println);
//    }
//
//    @Override //레코드 하나를 Map으로 가져오기
//    public void selectTrainingMap(String id) {
//        Map<String, Object> result = jdbcTemplate.queryForMap(
//                " SELECT * FROM traning WHERE id = ?",
//                id);
//        result.forEach((k, v) -> System.out.println(k + " : " + v));
//    }
//
//    @Override
//    public void selectTrainingMapList() {
//        List<Map<String, Object>> result = jdbcTemplate.queryForList(
//                " SELECT * FROM traning");
//
//        result.forEach(System.out::println);
//    }
//
//    @Override
//    public void selectTraining(String id) {
//        Training result = jdbcTemplate.queryForObject(
//                " SELECT * FROM traning WHERE id = ?",
//                new DataClassRowMapper<>(Training.class), id);
//        System.out.println("result : " + result.toString());
//    }
//
//    @Override
//    public void selectTrainingList() {
//        List<Training> result = jdbcTemplate.query(
//                " SELECT * FROM traning",
//                new DataClassRowMapper<>(Training.class));
//        result.forEach(System.out::println);
//    }
//
//    @Override
//    public List<Training> selectAll() {
//        System.out.println("DB에서 데이터 가져오기 ----------- ");
//
////        try(Connection con =  dataSource.getConnection();
////            Statement stmt = con.createStatement() ){
////            System.out.println("con ok");
////            System.out.println("stmt ok");
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        }
//        return List.of();
//    }
//
//    @Override // 레코드 추가하기
//    public void insert(Training training) {
//        int result = jdbcTemplate.update(
//                " INSERT INTO traning VALUES (?, ?, ?, ?, ?, ?)",
//                training.getId(),
//                training.getTitle(),
//                training.getStartDateTime(),
//                training.getEndDateTime(),
//                training.getReserved(),
//                training.getCapacity());
//        System.out.println(result == 1 ? "insert ok" : "insert not ok");
//    }
//
//    @Override //레코드 변경하기
//    public void update(Training training) {
//        int result = jdbcTemplate.update(
//                " UPDATE traning SET title = ?, Capacity = ? WHERE id = ?",
//                training.getTitle(),
//                training.getCapacity(),
//                training.getId());
//        System.out.println(result == 1 ? "UPDATE ok" : "UPDATE not ok");
//    }
//
//    @Override
//    public void delete(String id) {
//        int result = jdbcTemplate.update(
//                " DELETE FROM traning WHERE id = ?",
//                id);
//        System.out.println(result == 1 ? "DELETE ok" : "DELETE not ok");
//    }
//}
