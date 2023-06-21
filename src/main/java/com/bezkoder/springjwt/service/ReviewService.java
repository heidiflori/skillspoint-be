package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.exception.ResourceNotFoundException;
import com.bezkoder.springjwt.exception.ReviewNotPermittedException;
import com.bezkoder.springjwt.exception.UserAlreadyEnrolledException;
import com.bezkoder.springjwt.models.Review;
import com.bezkoder.springjwt.models.Training;
import com.bezkoder.springjwt.repository.EnrolledUserRepository;
import com.bezkoder.springjwt.repository.ReviewRepository;
import com.bezkoder.springjwt.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bezkoder.springjwt.models.EnrolledUser;

import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private EnrolledUserRepository enrolledUserRepository;

    public void addReview(Review review) throws ReviewNotPermittedException, ResourceNotFoundException {
        Integer trainingId = review.getTraining().getId();
        Long userId = review.getUser().getId();


        Optional<Training> existingTraining = trainingRepository.findById(trainingId);
        if(!existingTraining.isPresent()) {
            throw new ResourceNotFoundException("Training cu id " + trainingId + " nu a fost gasit");
        }

        Training training = existingTraining.get();

        Optional<EnrolledUser> existingEnrollment = enrolledUserRepository.findByUserIdAndTrainingId(userId, trainingId);
        if(!existingEnrollment.isPresent()) {
            throw new ResourceNotFoundException("Utilizatorul cu id " + userId + " nu a fost inscris la trainingul cu id " + trainingId);
        }

        EnrolledUser enrolledUser = existingEnrollment.get();


        if (training.getStatus().equals("finished") && enrolledUser.getAttendedTraining().equals("Yes")) {
            reviewRepository.save(review);
        } else {
            throw new ReviewNotPermittedException("Cannot add a review for the training. Please make sure the training is finished and you were present.");
        }
    }

    private boolean containsEnrolledUser(Training training, int userId) {
        return training.getEnrolledUsers()
                .stream()
                .map(EnrolledUser::getUser)
                .anyMatch(user -> user.getId() == userId);
    }

    public void deleteReview(int id) {
        Review review = reviewRepository.findById(id);
        if (review != null) {
            reviewRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Review not found.");
        }
    }
}
