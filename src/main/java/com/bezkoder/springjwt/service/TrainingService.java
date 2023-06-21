package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.exception.MaximumCapacityException;
import com.bezkoder.springjwt.exception.ResourceNotFoundException;
import com.bezkoder.springjwt.exception.TrainingOngoingException;
import com.bezkoder.springjwt.exception.UserAlreadyEnrolledException;
import com.bezkoder.springjwt.models.EnrolledUser;
import com.bezkoder.springjwt.models.Training;
import com.bezkoder.springjwt.repository.EnrolledUserRepository;
import com.bezkoder.springjwt.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {
    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    EnrolledUserRepository enrolledUserRepository;

    public void save(Training training) {
        trainingRepository.save(training);
    }

    public Training getById(int id) {
        return trainingRepository.findById(id);
    }

    public void deleteById(int id) {
        trainingRepository.deleteById(id);
    }

    public void updateTraining(Training training) {
        trainingRepository.save(training);
    }

    public void approveTraining(int id) {
        trainingRepository.approveTraining(id);
    }

    public List<Training> getByAdminApproval(String adminApproval) {
        return trainingRepository.findByAdminApproval(adminApproval);
    }

    public List<Training> getPendingTrainings() {
        return trainingRepository.findByAdminApproval("pending");
    }

    public List<Training> getApprovedTrainings() {
        return trainingRepository.findByAdminApproval("approved");
    }

    public List<Training> getByType(String type) {
        return trainingRepository.findByType(type);
    }

    public Training incrementOccupiedSlots(Long userId, Integer trainingId) throws ResourceNotFoundException, MaximumCapacityException, TrainingOngoingException, UserAlreadyEnrolledException{
        Optional<Training> existingTraining = trainingRepository.findById(trainingId);
        if(!existingTraining.isPresent()) {
            throw new ResourceNotFoundException("Training cu id " + trainingId + " nu a fost gasit");
        }

        Training training = existingTraining.get();

        Optional<EnrolledUser> existingEnrollment = enrolledUserRepository.findByUserIdAndTrainingId(userId, trainingId);
        if(existingEnrollment.isPresent()) {
            throw new UserAlreadyEnrolledException("Utilizatorul cu id " + userId + " este deja înscris la trainingul cu id " + trainingId);
        }

        Date now = new Date();
        if (training.getStartingDate().compareTo(now) <= 0) {
            throw new TrainingOngoingException("Inscrierea la training-ul cu id " + trainingId + " nu este posibila deoarece data de start a trecut");
        }
        if (training.getOccupiedSlots() < training.getMaximumSlots()) {
            training.setOccupiedSlots(training.getOccupiedSlots() + 1);
            return trainingRepository.save(training);
        } else {
            throw new MaximumCapacityException("Nu mai sunt sloturi disponibile pentru training-ul cu id " + trainingId);
        }
    }

}
