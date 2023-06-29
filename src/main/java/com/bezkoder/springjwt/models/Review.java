package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reviews")
public class Review {

    //DB: PK
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //DB: FK users
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //DB: FK trainings
    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comment")
    private String comment;

}
