//this file makes it so that when someone visits the website, they get
//automatically redirected to the ratings page
package com.example.ratestaff.controller;
//imports from spring framework
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "redirect:/ratings";
    }
}