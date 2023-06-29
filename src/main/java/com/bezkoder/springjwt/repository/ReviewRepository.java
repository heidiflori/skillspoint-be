package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Review findById(int id);

    void deleteById(int id);

    List<Review> findByUserId(Integer userId);

    List<Review> findByTrainingId(Integer id);
}
