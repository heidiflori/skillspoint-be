package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Review;
import com.bezkoder.springjwt.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('TRAINER') or hasRole('ADMIN')")
    public void addReview(@RequestBody Review review) {
        reviewService.addReview(review);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteReview(@RequestParam(name = "id") int id) {
        reviewService.deleteReview(id);
    }
}
