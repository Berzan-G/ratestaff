//this controller handles web routes
package com.example.ratestaff.controller;
//imports
import com.example.ratestaff.model.RoleType;
import com.example.ratestaff.model.StaffRating;
import com.example.ratestaff.repository.StaffRatingRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.ratestaff.design.ProfileFactory;

//create controller class
@Controller //the class will return thymeleaf pages
@RequestMapping("/ratings") //all routes inside start with /ratings
public class StaffRatingController{
    //add repository
    private final StaffRatingRepository repository;
    public StaffRatingController(StaffRatingRepository repository){
        this.repository = repository; //gives the controller access to database methods
    }

    //add index/list route
    @GetMapping
    public String index(Model model){
        model.addAttribute("ratings", repository.findAll());
        return "index";
    }

    //add detail route:
    //find one rating by id
    //if it exists, show the detail page
    //if not, redirect back to the list
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model){
        StaffRating rating = repository.findById(id).orElse(null);
        if (rating == null) return "redirect:/ratings";
        model.addAttribute("rating", rating);
        model.addAttribute("displayTitle", ProfileFactory.createProfile(rating.getRoleType()).displayTitle());
        return "detail";
    }

    //add create form route
    //create an empty staffrating, add role options, and return to form page
    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("rating", new StaffRating());
        model.addAttribute("roleTypes", RoleType.values());
        return "form";
    }

    //add create-submit route
    //recieve form data, check validation errors, check duplicate email,
    //if invalid, return form again, if valid, save and direct
    @PostMapping
    public String create(
        @Valid @ModelAttribute("rating") StaffRating rating, BindingResult result, Model model
    ){
        if (repository.existsByEmail(rating.getEmail())){
            result.rejectValue("email", "duplicate", "Email already used.");
        }

        if (result.hasErrors()){
            model.addAttribute("roleTypes", RoleType.values());
            return "form";
        }

        repository.save(rating);
        return "redirect:/ratings";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
    StaffRating rating = repository.findById(id).orElse(null);

    if (rating == null) {
        return "redirect:/ratings";
    }

    model.addAttribute("rating", rating);
    model.addAttribute("roleTypes", RoleType.values());
    return "form";
    }

    @PostMapping("/{id}")
    public String update(
        @PathVariable Long id,
        @Valid @ModelAttribute("rating") StaffRating rating,
        BindingResult result,
        Model model) {

    if (repository.existsByEmailAndIdNot(rating.getEmail(), id)) {
        result.rejectValue("email", "duplicate", "Email is already used.");
    }

    if (result.hasErrors()) {
        model.addAttribute("roleTypes", RoleType.values());
        return "form";
    }

    rating.setId(id);
    repository.save(rating);
    return "redirect:/ratings/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deleteConfirm(@PathVariable Long id, Model model) {
    StaffRating rating = repository.findById(id).orElse(null);

    if (rating == null) {
        return "redirect:/ratings";
    }

    model.addAttribute("rating", rating);
    return "delete-confirm";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
    repository.deleteById(id);
    return "redirect:/ratings";
    }
}