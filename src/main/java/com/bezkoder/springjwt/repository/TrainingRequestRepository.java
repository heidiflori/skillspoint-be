package com.bezkoder.springjwt.repository;


import com.bezkoder.springjwt.models.TrainingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRequestRepository extends JpaRepository<TrainingRequest,Integer> {

    List<TrainingRequest> findByAdminApproval(String adminApproval);

}
