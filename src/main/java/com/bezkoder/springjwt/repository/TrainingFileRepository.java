package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.EnrolledUser;
import com.bezkoder.springjwt.models.TrainingFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingFileRepository extends JpaRepository<TrainingFile, Integer> {

    @Query("SELECT f FROM TrainingFile f WHERE f.training.id = :trainingId")
    List<TrainingFile> findTrainingFilesInTraining(@Param("trainingId") Integer trainingId);

    @Query("SELECT f FROM TrainingFile f WHERE f.fileName LIKE :fileIdPrefix%")
    List<TrainingFile> findTrainingFilesStartingWith(@Param("fileIdPrefix") String fileIdPrefix);




}
