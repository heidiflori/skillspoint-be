package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Integer> {
    Training findById(int id);

    Training save(Training training);

    @Modifying
    @Query("update Training t set t.status = 'deleted' where t.id = :id")
    void deleteById(int id);

    @Modifying
    @Query("update Training t set t.adminApproval = 'approved' where t.adminApproval = 'pending' and t.id = :id")
    void approveTraining(int id);

    @Query("select t from Training t where t.adminApproval = 'pending'")
    List<Training> findByAdminApprovalIsPending();

    @Query("select t from Training t where t.adminApproval = 'approved'")
    List<Training> findByAdminApprovalIsApproved(String adminApproval);

    @Query("select t from Training t where t.adminApproval = :adminApproval")
    List<Training> findByAdminApproval(String adminApproval);

    List<Training> findByType(String type);
}
