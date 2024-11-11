package edu.example.training.repository;

import edu.example.training.entity.Reservation;
import edu.example.training.entity.Training;

public interface ReservationRepository {
    boolean insert(Reservation reservation);

    Reservation selectById(String id);

    Training selectByTrainingId(String id);
}
