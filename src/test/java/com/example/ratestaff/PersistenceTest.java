package com.example.ratestaff;

import com.example.ratestaff.model.RoleType;
import com.example.ratestaff.model.StaffRating;
import com.example.ratestaff.repository.StaffRatingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class PersistenceTest {

    @Autowired
    private StaffRatingRepository repository;

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
    void savingAndRetrievingEntryWorks() {
        StaffRating rating = validRating();

        StaffRating saved = repository.save(rating);

        assertNotNull(saved.getId());

        StaffRating found = repository.findById(saved.getId()).orElse(null);

        assertNotNull(found);
        assertEquals("Gomer Pyle", found.getName());
        assertEquals("gpyle@sfu.ca", found.getEmail());
    }

    @Test
    void deleteRemovesEntry() {
        StaffRating rating = validRating();

        StaffRating saved = repository.save(rating);
        Long id = saved.getId();

        repository.deleteById(id);

        assertFalse(repository.findById(id).isPresent());
    }
}