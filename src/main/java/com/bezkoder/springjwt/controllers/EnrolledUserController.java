package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.exception.MaximumCapacityException;
import com.bezkoder.springjwt.exception.ResourceNotFoundException;
import com.bezkoder.springjwt.exception.TrainingOngoingException;
import com.bezkoder.springjwt.exception.UserAlreadyEnrolledException;
import com.bezkoder.springjwt.models.EnrolledUser;
import com.bezkoder.springjwt.service.EnrolledUserService;
import com.bezkoder.springjwt.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/enrolled-users")
public class EnrolledUserController {

    @Autowired
    EnrolledUserService enrolledUserService;
    @Autowired
    TrainingService trainingService;

    @PostMapping("/enrol")
    @PreAuthorize("hasRole('USER') or hasRole('TRAINER') or hasRole('ADMIN')")
    public ResponseEntity<?> save(@RequestBody EnrolledUser enrolledUser) throws ResourceNotFoundException, MaximumCapacityException, TrainingOngoingException, UserAlreadyEnrolledException {
        enrolledUser.setAttendedTraining("No");
        trainingService.incrementOccupiedSlots(enrolledUser.getUser().getId(),enrolledUser.getTraining().getId());
        enrolledUserService.save(enrolledUser);
        return ResponseEntity.ok("Enrolled");
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        enrolledUserService.deleteEnrolledUser(id);
        return ResponseEntity.ok("User enrol deleted");
    }

    @PutMapping("/attendance/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> attendedTraining(@PathVariable Integer id){
        enrolledUserService.attendedTraining(id);
        return ResponseEntity.ok("User attended the training");
    }

    @GetMapping("/training/{trainingId}")
    public List<EnrolledUser> getEnrolledUsersForTraining(@PathVariable Integer trainingId) {
        return enrolledUserService.findUsersEnrolledInTraining(trainingId);
    }



}
