package com.example.ratestaff;

import com.example.ratestaff.model.RoleType;
import com.example.ratestaff.model.StaffRating;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private StaffRating validRating() {
        StaffRating rating = new StaffRating();
        rating.setName("Gomer Pyle");
        rating.setEmail("gpyle@sfu.ca");
        rating.setRoleType(RoleType.PROF);
        rating.setClarity(5);
        rating.setNiceness(5);
        rating.setKnowledgeableScore(5);
        rating.setComment("Good instructor.");
        return rating;
    }

    @Test
    void invalidEmailIsRejected() {
        StaffRating rating = validRating();
        rating.setEmail("not-an-email");

        Set<ConstraintViolation<StaffRating>> violations = validator.validate(rating);

        assertFalse(violations.isEmpty());
    }

    @Test
    void missingNameIsRejected() {
        StaffRating rating = validRating();
        rating.setName("");

        Set<ConstraintViolation<StaffRating>> violations = validator.validate(rating);

        assertFalse(violations.isEmpty());
    }

    @Test
    void outOfRangeScoreIsRejected() {
        StaffRating rating = validRating();
        rating.setClarity(11);

        Set<ConstraintViolation<StaffRating>> violations = validator.validate(rating);

        assertFalse(violations.isEmpty());
    }
}