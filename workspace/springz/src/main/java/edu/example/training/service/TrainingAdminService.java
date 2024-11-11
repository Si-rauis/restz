package edu.example.training.service;

import edu.example.training.entity.Training;
import edu.example.training.input.TrainingInput;
import java.util.List;

public interface TrainingAdminService {
    Training findById(String trainingId);

    List<Training> findByTraining();

    void modify(TrainingInput trainingInput);

    Training add(TrainingInput trainingInput);

    void delete(String trainingId);
}
