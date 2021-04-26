package ru.kpfu.itis.transportprojectweb.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public";
    }

    @GetMapping("/user")
    public String userAccess() {
        return "User";
    }

    @GetMapping("/admin")
    public String adminAccess() {
        return "Admin";
    }
}
