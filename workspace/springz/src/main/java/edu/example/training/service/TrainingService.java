package edu.example.training.service;

import edu.example.training.entity.Training;
import edu.example.training.input.TrainingInput;

import java.util.List;

public interface TrainingService {

    Training findById(String trainingId);

    List<Training> findByTraining();

}
