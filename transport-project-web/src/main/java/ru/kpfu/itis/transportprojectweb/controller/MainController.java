package ru.kpfu.itis.transportprojectweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("")
    public String mainPage() {

        return "mainPage";
    }

    @GetMapping("/search")
    public String getSearchPage() {
        return "searchPage";
    }

}
