package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Review findById(int id);

    void deleteById(int id);
}
