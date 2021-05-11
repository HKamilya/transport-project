package ru.kpfu.itis.transportprojectweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignInController {
    @GetMapping(value = "/signIn")
    public String signInPage() {
        return "signInPage";
    }

}
