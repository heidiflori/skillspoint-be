package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Training;
import com.bezkoder.springjwt.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainings")
public class TrainingController {
    @Autowired
    TrainingService trainingService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('TRAINER') or hasRole('ADMIN')")
    public ResponseEntity<?> addTraining(@RequestBody Training training) {
        trainingService.save(training);
        return ResponseEntity.ok("Training saved.");
    }

    @PutMapping("/edit")
    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateTraining(@RequestBody Training training) {
        trainingService.updateTraining(training);
        return ResponseEntity.ok("Training updated.");
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTrainingById(@RequestParam(name = "id") int id) {
        trainingService.deleteById(id);
        return ResponseEntity.ok("Training deleted");
    }

    @PutMapping("/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approveTraining(@RequestParam(name = "id") int id) {
        trainingService.approveTraining(id);
        return ResponseEntity.ok("Training approved.");
    }

    @GetMapping("/pending-trainings")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getPendingTrainings() {
        return ResponseEntity.ok(trainingService.getPendingTrainings());
    }

    @GetMapping("/approved-trainings")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER') or hasRole('USER')")
    public ResponseEntity<?> getApprovedTrainings(String adminApproval) {
        return ResponseEntity.ok(trainingService.getApprovedTrainings(adminApproval));
    }

    @GetMapping("/type")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER') or hasRole('USER')")
    public ResponseEntity<?> getTrainingsByType(@RequestParam(name = "type") String type) {
        return ResponseEntity.ok(trainingService.getByType(type));
    }
}
