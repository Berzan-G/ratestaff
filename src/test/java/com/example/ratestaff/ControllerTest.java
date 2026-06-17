package com.example.ratestaff;

import com.example.ratestaff.model.RoleType;
import com.example.ratestaff.model.StaffRating;
import com.example.ratestaff.repository.StaffRatingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
    void getIndexReturnsOkAndHasRatingsModel() throws Exception {
        repository.deleteAll();
        repository.save(validRating());

        mockMvc.perform(get("/ratings"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("ratings"));
    }

    @Test
    void postCreateSuccessRedirects() throws Exception {
        repository.deleteAll();

        mockMvc.perform(post("/ratings")
                .param("name", "Alex Smith")
                .param("email", "alex@sfu.ca")
                .param("roleType", "TA")
                .param("clarity", "8")
                .param("niceness", "9")
                .param("knowledgeableScore", "7")
                .param("comment", "Helpful."))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ratings"));
    }

    @Test
    void postCreateFailureReturnsFormWithErrors() throws Exception {
        repository.deleteAll();

        mockMvc.perform(post("/ratings")
                .param("name", "")
                .param("email", "bad-email")
                .param("roleType", "TA")
                .param("clarity", "11")
                .param("niceness", "9")
                .param("knowledgeableScore", "7")
                .param("comment", "Helpful."))
                .andExpect(status().isOk())
                .andExpect(view().name("form"))
                .andExpect(model().hasErrors());
    }
}