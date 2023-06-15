package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.TrainingRequest;
import com.bezkoder.springjwt.repository.TrainingRequestRepository;
import org.hibernate.sql.results.internal.RowTransformerSingularReturnImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingRequestService {


    @Autowired
    TrainingRequestRepository trainingRequestRepository;

    public TrainingRequest save(TrainingRequest trainingRequest){
        return trainingRequestRepository.save(trainingRequest);
    }

    public TrainingRequest approveRequest(Integer id){
        TrainingRequest trainingRequest = trainingRequestRepository.findById(id).get();
        trainingRequest.setAdminApproval("approved");
        return trainingRequestRepository.save(trainingRequest);
    }

    public void deleteRequest(Integer id) {
        trainingRequestRepository.deleteById(id);
    }

    public TrainingRequest incrementLikesCounter(Integer id) {
        TrainingRequest trainingRequest = trainingRequestRepository.findById(id).get();
        trainingRequest.setLikesCounter(trainingRequest.getLikesCounter() + 1);
        return trainingRequestRepository.save(trainingRequest);
    }

    public List<TrainingRequest> getApprovedRequests(){
        return trainingRequestRepository.findByAdminApproval("approved");
    }

    public List<TrainingRequest> getPendingRequests(){
        return trainingRequestRepository.findByAdminApproval("pending");
    }

}
