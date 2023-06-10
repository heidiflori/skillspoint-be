package com.bezkoder.springjwt.models;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trainings")
public class Training {

    //DB: PK
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "trainer")
    private String trainer;

    @Column(name = "description")
    private String description;

    @Column(name = "occupied_slots")
    private int occupiedSlots;

    @Column(name = "maximum_slots")
    private int maxiumSlots;

    @Column(name = "type")
    private String type;

    @Column(name = "duration")
    private Double duration;

    @Column(name = "status")
    private String status;

    @Column(name = "admin_approval")
    private String adminApproval;

    //DB: FK reviews
    @OneToMany(mappedBy = "training")
    private Set<Review> reviews;


}
