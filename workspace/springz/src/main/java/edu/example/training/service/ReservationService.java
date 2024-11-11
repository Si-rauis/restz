package edu.example.training.service;

import edu.example.training.entity.Reservation;
import edu.example.training.entity.StudentType;
import edu.example.training.entity.Training;
import edu.example.training.exception.CapacityOverException;
import edu.example.training.input.ReservationInput;

import java.util.List;

public interface ReservationService {
    List<StudentType> findAllStudentType();

    Reservation reserve(ReservationInput reservationInput) throws CapacityOverException;

    Reservation findById(String id);

    Training findTrainingById(String id);
}
