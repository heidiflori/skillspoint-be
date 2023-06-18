package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Review;
import com.bezkoder.springjwt.models.Training;
import com.bezkoder.springjwt.repository.ReviewRepository;
import com.bezkoder.springjwt.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bezkoder.springjwt.models.EnrolledUser;
@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    public void addReview(Review review) {
        int trainingId = review.getTraining().getId();
        Training training = trainingRepository.findById(trainingId);
        if (training != null && training.getStatus().equals("finished") && containsEnrolledUser(training, Math.toIntExact(review.getUser().getId()))) {
            reviewRepository.save(review);
        } else {
            throw new IllegalArgumentException("Cannot add a review for the training. Please make sure the training is finished and you are enrolled.");
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
