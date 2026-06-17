package com.example.ratestaff.model; //tells java where the file lives

import java.time.LocalDateTime; //used for createdAt and updatedAt
//private and public elements are stored in the same area, with prefixes for each

//add more imports that let you use validation annotations
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//additional imports
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity //this tells Spring that the class should be mapped to a database table.
public class StaffRating{
    
    //private fields
    //mark id as the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //annotations for name (constraints)
    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters.")
    private String name;

    //validation for email
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid (ex. name@example.com)")
    private String email;
    
    //validation for roletype
    //notnull is used for fields where you make a selection or integer types. 
    @NotNull(message = "A role type is required.")
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    //cant just use size, as doesnt work for this data type. need to use min and max
    @NotNull(message = "Clarity score is required")
    @Min(value = 1, message = "Clarity must be at least 1")
    @Max(value = 10, message = "Clarity must be at most 10")
    private Integer clarity;

    @NotNull(message = "Niceness score is required")
    @Min(value = 1, message = "Niceness must be at least 1")
    @Max(value = 10, message = "Niceness must be at most 10")
    private Integer niceness;

    @NotNull(message = "Knowledgeable score is required")
    @Min(value = 1, message = "Knowledgeable score must be at least 1")
    @Max(value = 10, message = "Knowledgeable score must be at most 10")
    private Integer knowledgeableScore;

    @Size(max = 500, message = "Comment must be 500 characters or less")
    private String comment;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //getter and setter methods
    public StaffRating(){}
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return name;
    }

    //forgot to add this, caused an error when running!
    public void setName(String name){
    this.name = name;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public RoleType getRoleType(){
        return roleType;
    }
    public void setRoleType(RoleType roleType){
        this.roleType = roleType;
    }
    public Integer getClarity() {
        return clarity;
    }
    public void setClarity(Integer clarity) {
        this.clarity = clarity;
    }
    public Integer getNiceness() {
        return niceness;
    }
    public void setNiceness(Integer niceness) {
        this.niceness = niceness;
    }
    public Integer getKnowledgeableScore() {
        return knowledgeableScore;
    }
    public void setKnowledgeableScore(Integer knowledgeableScore) {
        this.knowledgeableScore = knowledgeableScore;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public double getOverallScore() {
        //initial conditions
        if (clarity == null || niceness == null || knowledgeableScore == null){
            return 0.0;
        }
        //return the average of these three fields if not 0
        return (clarity + niceness + knowledgeableScore) / 3.0;
    }

    //automatic timestamp methods for recording when an item is logged into the database
    @PrePersist
    public void onCreate (){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    public void onUpdate(){
        updatedAt = LocalDateTime.now();
    }
}