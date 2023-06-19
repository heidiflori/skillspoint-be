package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.EnrolledUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolledUserRepository extends JpaRepository<EnrolledUser,Integer> {

}
