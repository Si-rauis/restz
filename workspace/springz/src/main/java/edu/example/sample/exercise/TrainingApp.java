//package edu.example.training;
//import com.zaxxer.hikari.HikariDataSource;
//import edu.example.training.entity.Reservation;
//import edu.example.training.exception.CapacityOverException;
//import edu.example.training.input.ReservationInput;
//import edu.example.training.service.ReservationService;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.*;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.support.JdbcTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import ch.qos.logback.classic.Level;
//import ch.qos.logback.classic.Logger;
//import org.slf4j.LoggerFactory;
//
//
//@Configuration
//@ComponentScan
//@EnableTransactionManagement        //트랜잭션 관리 활성화
//public class TrainingApp {
//    @Bean
//    public DataSource dataSource() {
//        //HikariCP와 MySQL로 dataSource 지정
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/testdb");
//        dataSource.setUsername("root");
//        dataSource.setPassword("7189");
//
//        //H2 내장 데이터베이스로 dataSource 지정
////      EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
//        return dataSource;
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    @Bean
//    PlatformTransactionManager transactionManager(DataSource dataSource) {
//        return new JdbcTransactionManager(dataSource);
//    }
//
//    public static void main(String[] args) {
//        ApplicationContext context =
//                new AnnotationConfigApplicationContext(TrainingApp.class);
//
//        //트랜잭션 제어 로그 레벨 설정 - application.properties 사용 X
//        ((Logger) LoggerFactory.getLogger( JdbcTransactionManager.class )).setLevel(Level.DEBUG);
//
//        ReservationService reservationService = (ReservationService) context.getBean(ReservationService.class);
//
//        //에약 입력 정보 객체
//        ReservationInput reservationInput = new ReservationInput();
//        reservationInput.setTrainingId("t03");
//        reservationInput.setName("김이박");
//        reservationInput.setPhone("010-1111-2222");
//        reservationInput.setEmailAddress("kim@example.com");
//        reservationInput.setStudent_type_id("FREELANCE");
//
//        //예약 신청 - 트랜잭션 시작 -------
//        //ReservationServiceImpl의 reserve() 호출
//        try {
//            Reservation reservation = reservationService.reserve(reservationInput);
//            System.out.println("예약 신청 ID : " + reservation.getId());
//        } catch (CapacityOverException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}