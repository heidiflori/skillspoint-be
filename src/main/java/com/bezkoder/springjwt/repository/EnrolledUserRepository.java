package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.EnrolledUser;
import com.bezkoder.springjwt.models.TrainingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrolledUserRepository extends JpaRepository<EnrolledUser,Integer> {

    @Query("SELECT e FROM EnrolledUser e WHERE e.training.id = :trainingId")
    List<EnrolledUser> findUsersEnrolledInTraining(@Param("trainingId") Integer trainingId);

    Optional<EnrolledUser> findByUserIdAndTrainingId(Long userId, Integer trainingId);

}
