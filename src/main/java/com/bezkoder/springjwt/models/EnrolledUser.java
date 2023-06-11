package com.bezkoder.springjwt.models;


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
@Table(name = "enrolled_users")
public class EnrolledUser {

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

    @Column(name = "attended_training")
    private String attendedTraining;


}
