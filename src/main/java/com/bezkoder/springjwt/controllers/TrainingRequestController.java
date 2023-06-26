package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.exception.ZeroResultsException;
import com.bezkoder.springjwt.models.TrainingRequest;
import com.bezkoder.springjwt.service.TrainingRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/skills/training-requests")
public class TrainingRequestController {

    @Autowired
    TrainingRequestService trainingRequestService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('TRAINER') or hasRole('ADMIN')")
    public ResponseEntity<?> save(@RequestBody TrainingRequest trainingRequest){
        trainingRequest.setAdminApproval("pending");
        trainingRequestService.save(trainingRequest);
        return ResponseEntity.ok("Request sent");
    }

    @PutMapping("/approve/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approveRequest(@PathVariable Integer id){
        trainingRequestService.approveRequest(id);
        return ResponseEntity.ok("Request approved");
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        trainingRequestService.deleteRequest(id);
        return ResponseEntity.ok("Request deleted");
    }

    @PostMapping("/like/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('TRAINER') or hasRole('ADMIN')")
    public ResponseEntity<?> incrementLikesCounter(@PathVariable Integer id) {
        trainingRequestService.incrementLikesCounter(id);
        return ResponseEntity.ok("Likes number incremented");
    }

    @GetMapping("/approved-requests")
    @PreAuthorize("hasRole('USER') or hasRole('TRAINER') or hasRole('ADMIN')")
    public ResponseEntity<?> findApprovedRequests() throws ZeroResultsException {
        return ResponseEntity.ok(trainingRequestService.getApprovedRequests());
    }

    @GetMapping("/pending-requests")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> findPendingRequests() throws ZeroResultsException{
        return ResponseEntity.ok(trainingRequestService.getPendingRequests());
    }

}
