package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.EnrolledUser;
import com.bezkoder.springjwt.models.TrainingFile;
import com.bezkoder.springjwt.repository.TrainingFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TrainingFileService {

    @Autowired
    TrainingFileRepository trainingFileRepository;

    public List<TrainingFile> findTrainingFilesInTraining(Integer trainingId) {
        return trainingFileRepository.findTrainingFilesInTraining(trainingId);
    }

    public void save(TrainingFile trainingFile){
        trainingFileRepository.save(trainingFile);
    }

    public List<TrainingFile> findTrainingFilesStartingWith(String fileIdPrefix) {
        return trainingFileRepository.findTrainingFilesStartingWith(fileIdPrefix);
    }



}
