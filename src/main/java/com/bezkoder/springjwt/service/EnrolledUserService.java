package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.EnrolledUser;
import com.bezkoder.springjwt.models.TrainingRequest;
import com.bezkoder.springjwt.repository.EnrolledUserRepository;
import com.bezkoder.springjwt.repository.TrainingRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<EnrolledUser> getTrainingsEnrolledByUserId(Integer id) {
        return enrolledUserRepository.findTrainingsEnrolledByUserId(id);
    }

    public void setAttendedTraining(Long userId, Integer trainingId) {
        Optional<EnrolledUser> enrolledUserOptional = enrolledUserRepository.findByUserIdAndTrainingId(userId, trainingId);
        EnrolledUser enrolledUser = enrolledUserOptional.orElseThrow(() -> new EntityNotFoundException("No EnrolledUser found with the provided userId and trainingId"));
        enrolledUser.setAttendedTraining("Yes");
        enrolledUserRepository.save(enrolledUser);
    }
}
