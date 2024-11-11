package edu.example.training.service;

import edu.example.training.entity.Training;
import edu.example.training.input.TrainingInput;
import edu.example.training.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {
    @Autowired  //field 인젝션
    private TrainingRepository trainingRepository;

    @Autowired  //constructor 인젝션
    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
        System.out.println("TrainingServiceImpl()----------------");
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Training findById(String trainingId) {
        System.out.println("TrainingServiceImpl()---------------");
        return trainingRepository.selectById(trainingId);
    }

    public List<Training> findByTraining(){
        System.out.println("TrainingServiceImpl()---------------");
        return trainingRepository.selectTrainingMapList();
    }

}











