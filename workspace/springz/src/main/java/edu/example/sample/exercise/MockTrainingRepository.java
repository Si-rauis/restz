package edu.example.sample.exercise;//package edu.example.training.repository;
//
//import edu.example.training.entity.Training;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MockTrainingRepository implements TrainingRepository{
//    @Override
//    public List<Training> selectAll() {
//        System.out.println("테스트 데이터 ---------------");
//
//        List<Training> trainings = new ArrayList<>();
//        for(int i = 0 ; i < 10 ; i++){
//            Training training = new Training();
//            training.setTitle("title_" + i);
//
//            trainings.add(training);
//        }
//        return trainings;
//    }
//}
