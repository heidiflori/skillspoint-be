package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Training;
import com.bezkoder.springjwt.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {
    @Autowired
    TrainingService trainingService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('TRAINER') or hasRole('ADMIN')")
    public Training addTraining(@RequestBody Training training) {
        return trainingService.save(training);
    }

    @PutMapping("/edit")
    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
    public void updateTraining(@RequestBody Training training) {
        trainingService.updateTraining(training);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTrainingById(@RequestParam(name = "id") int id) {
        trainingService.deleteById(id);
    }

    @PutMapping("/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public void approveTraining(@RequestParam(name = "id") int id) {
        trainingService.approveTraining(id);
    }

    @GetMapping("/pending-trainings")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Training> getPendingTrainings() {
        return trainingService.getPendingTrainings();
    }

    @GetMapping("/approved-trainings")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER') or hasRole('USER')")
    public List<Training> getApprovedTrainings(String adminApproval) {
        return trainingService.getApprovedTrainings(adminApproval);
    }
}
