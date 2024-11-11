//package edu.example.sample;
//
//import edu.example.training.entity.Training;
//import edu.example.training.repository.TrainingRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TrainingServiceImpl implements TrainingService {
//    @Autowired  //field 인젝션
//    private TrainingRepository trainingRepository;
//
//    @Autowired  //constructor 인젝션
//    public TrainingServiceImpl(TrainingRepository trainingRepository) {
//        this.trainingRepository = trainingRepository;
//    }
//
//    @Autowired  //setter 인젝션
//    public void setTrainingRepository(TrainingRepository trainingRepository) {
//        this.trainingRepository = trainingRepository;
//    }
//
//    @Override
//    public List<Training> findAll() {
////        TrainingRepository tr = new JdbcTrainingRepository();
////                           tr = new MockTrainingRepository();
//        return trainingRepository.selectAll();
//    }
//
//    @Override
//    public void findTitle(String id) {
//        trainingRepository.selectTitle(id);
//    }
//
//    @Override
//    public void findTitle(String id, String title) {
//        trainingRepository.selectTitle(id, title);
//    }
//
//    @Override
//    public void findStart(String id) {
//        trainingRepository.selectStart(id);
//    }
//
//    @Override
//    public void getReserveds() {
//        trainingRepository.selectReserveds();
//    }
//
//    @Override
//    public void getTrainingMap(String id) {
//        trainingRepository.selectTrainingMap(id);
//    }
//
//    @Override
//    public void getTrainingMapList() {
//        trainingRepository.selectTrainingMapList();
//    }
//
//    @Override
//    public void getTrainingEntity(String id) {
//        trainingRepository.selectTraining(id);
//    }
//
//    @Override
//    public void getTrainingsList() {
//        trainingRepository.selectTrainingList();
//    }
//
//    @Override
//    public void add(Training training) {
//        trainingRepository.insert(training);
//    }
//
//    @Override
//    public void update(Training training) {
//        trainingRepository.update(training);
//    }
//
//    @Override
//    public void delete(String id) {
//        trainingRepository.delete(id);
//    }
//
//
//}
//
//
//
//
//
//
//
//
//
//
//
