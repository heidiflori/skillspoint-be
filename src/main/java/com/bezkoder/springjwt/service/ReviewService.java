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

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private EnrolledUserRepository enrolledUserRepository;

    public void addReview(Review review){
        reviewRepository.save(review);
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


    public List<Review> findByUserId(Integer userId){
        return reviewRepository.findByUserId(userId);
    }

    public List<Review> findByTrainingId(Integer trainingId){
        return reviewRepository.findByTrainingId(trainingId);
    }
}
