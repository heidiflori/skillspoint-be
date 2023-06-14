package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Training;
import com.bezkoder.springjwt.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {
    @Autowired
    TrainingRepository trainingRepository;

    public Training save(Training training) {
        return trainingRepository.save(training);
    }

    public Training getById(int id) {
        return trainingRepository.findById(id);
    }

    public void deleteById(int id) {
        trainingRepository.deleteById(id);
    }

    public Training updateTraining(Training training) {
        return trainingRepository.save(training);
    }

    public void approveTraining(int id) {
        trainingRepository.approveTraining(id);
    }

    public List<Training> getByAdminApproval(String adminApproval) {
        return trainingRepository.findByAdminApproval(adminApproval);
    }

    public List<Training> getPendingTrainings() {
        return trainingRepository.findByAdminApprovalIsPending();
    }

    public List<Training> getApprovedTrainings(String adminApproval) {
        return trainingRepository.findByAdminApprovalIsApproved(adminApproval);
    }

    public List<Training> getByType(String type) {
        return trainingRepository.findByType(type);
    }
}
