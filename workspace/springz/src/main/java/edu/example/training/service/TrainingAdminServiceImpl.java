package edu.example.training.service;

import edu.example.training.controller.TrainingAdminRestController;
import edu.example.training.entity.Training;
import edu.example.training.input.TrainingInput;
import edu.example.training.repository.JdbcTrainingRepository;
import edu.example.training.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrainingAdminServiceImpl implements TrainingAdminService{

    @Autowired  //field 인젝션
    private TrainingRepository trainingRepository;

    @Override
    public Training findById(String trainingId) {
        return null;
    }

    @Override
    public List<Training> findByTraining() {
        return List.of();
    }

    @Override
    public void modify(TrainingInput trainingInput) {
        Training training = new Training(
                trainingInput.getId(),
                trainingInput.getTitle(),
                trainingInput.getStartDateTime(),
                trainingInput.getEndDateTime(),
                trainingInput.getReserved(),
                trainingInput.getCapacity()
        );
        trainingRepository.update(training);
    }

    @Override
    public Training add(TrainingInput trainingInput) {
        Training training = new Training(
                trainingInput.getId(),
                trainingInput.getTitle(),
                trainingInput.getStartDateTime(),
                trainingInput.getEndDateTime(),
                trainingInput.getReserved(),
                trainingInput.getCapacity()
        );
        trainingRepository.insert(training);
        return training;
    }

    @Override
    public void delete(String trainingId) {
        trainingRepository.delete(trainingId);
    }
}
