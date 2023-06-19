package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Review;
import com.bezkoder.springjwt.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('TRAINER') or hasRole('ADMIN')")
    public ResponseEntity<?> addReview(@RequestBody Review review) {
        reviewService.addReview(review);
        return ResponseEntity.ok("Review added.");
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteReview(@RequestParam(name = "id") int id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review deleted.");
    }
}
