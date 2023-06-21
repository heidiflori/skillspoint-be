package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.EnrolledUser;
import com.bezkoder.springjwt.models.TrainingRequest;
import com.bezkoder.springjwt.repository.EnrolledUserRepository;
import com.bezkoder.springjwt.repository.TrainingRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolledUserService {

    @Autowired
    EnrolledUserRepository enrolledUserRepository;

    public EnrolledUser save(EnrolledUser enrolledUser){
        return enrolledUserRepository.save(enrolledUser);
    }

    public void deleteEnrolledUser(Integer id) {
        enrolledUserRepository.deleteById(id);
    }

    public EnrolledUser attendedTraining(Integer id){
        EnrolledUser enrolledUser = enrolledUserRepository.findById(id).get();
        enrolledUser.setAttendedTraining("Yes");
        return enrolledUserRepository.save(enrolledUser);
    }

    public List<EnrolledUser> findUsersEnrolledInTraining(Integer trainingId) {
        return enrolledUserRepository.findUsersEnrolledInTraining(trainingId);
    }


}
