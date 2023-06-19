package com.bezkoder.springjwt.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_username")
    private String trainer;

    @Column(name = "description")
    private String description;

    @Column(name = "occupied_slots")
    private Integer occupiedSlots;

    @Column(name = "maximum_slots")
    private Integer maximumSlots;

    @Column(name = "type")
    private String type;

    @Column(name = "starting_date")
    private Date startingDate;

    @Column(name = "duration")
    private Double duration;

    @Column(name = "status")
    private String status;

    @Column(name = "admin_approval")
    private String adminApproval;

    //DB: FK reviews
    @OneToMany(mappedBy = "training")
    private Set<Review> reviews;

    //DB: FK enrolled_users
    @OneToMany(mappedBy = "training")
    private Set<EnrolledUser> enrolledUsers;


}
