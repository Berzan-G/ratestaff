//this file, the staff rating repository, lets the application talk
//to the database

package com.example.ratestaff.repository;

import com.example.ratestaff.model.StaffRating;
import org.springframework.data.jpa.repository.JpaRepository;

//meaning: this repository manages staffrating objects, and their id type is long.
//includes methods to check if the email is unique.
public interface StaffRatingRepository extends JpaRepository<StaffRating, Long>{
    boolean existsByEmail(String email); //checks if an email is already used
    boolean existsByEmailAndIdNot(String email, long id); //checks if an email belongs to someone else
}