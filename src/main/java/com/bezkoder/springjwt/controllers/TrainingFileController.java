package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.models.TrainingFile;
import com.bezkoder.springjwt.service.TrainingFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/skills/training-files")
public class TrainingFileController {


    @Autowired
    TrainingFileService trainingFileService;

    @GetMapping("/{trainingId}")
    @PreAuthorize("hasRole('USER') or hasRole('TRAINER') or hasRole('ADMIN')")
    public ResponseEntity<List<TrainingFile>> getTrainingFilesByTrainingId(@PathVariable("trainingId") Integer trainingId) {
        try {
            List<TrainingFile> files = trainingFileService.findTrainingFilesInTraining(trainingId);
            return new ResponseEntity<>(files, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
