package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.exception.ResourceNotFoundException;
import com.bezkoder.springjwt.models.Training;
import com.bezkoder.springjwt.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/skills/trainings")
public class TrainingController {
    @Autowired
    TrainingService trainingService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('TRAINER') or hasRole('ADMIN')")
    public ResponseEntity<?> getTrainingById(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(trainingService.findById(id));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('TRAINER') or hasRole('ADMIN')")
    public ResponseEntity<?> addTraining(@RequestBody Training training) {
        training.setOccupiedSlots(0);
        training.setAdminApproval("pending");
        training.setStatus("upcoming");
        trainingService.save(training);
        return ResponseEntity.ok("Training saved.");
    }

    @PutMapping("/edit")
    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateTraining(@RequestBody Training training) {
        trainingService.updateTraining(training);
        return ResponseEntity.ok("Training updated.");
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTrainingById(@PathVariable Integer id) {
        trainingService.deleteById(id);
        return ResponseEntity.ok("Training deleted");
    }

    @PutMapping("/approve/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approveTraining(@PathVariable Integer id) {
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
    public ResponseEntity<?> getApprovedTrainings() {
        return ResponseEntity.ok(trainingService.getApprovedTrainings());
    }

    @GetMapping("/type")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER') or hasRole('USER')")
    public ResponseEntity<?> getTrainingsByType(@RequestParam(name = "type") String type) {
        return ResponseEntity.ok(trainingService.getByType(type));
    }
}
