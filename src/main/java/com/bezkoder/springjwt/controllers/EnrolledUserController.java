package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.models.EnrolledUser;
import com.bezkoder.springjwt.service.EnrolledUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/enrolled-users")
public class EnrolledUserController {

    @Autowired
    EnrolledUserService enrolledUserService;

    @PostMapping("/enrol")
    @PreAuthorize("hasRole('USER') or hasRole('TRAINER') or hasRole('ADMIN')")
    public ResponseEntity<?> save(@RequestBody EnrolledUser enrolledUser){
        enrolledUser.setAttendedTraining("No");
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

}
