package ru.kpfu.itis.transportprojectweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;

@Controller
public class AdminProfileController {

    @GetMapping("/admin")
    public String adminPage() {
        return "adminProfile";
    }

    @PostMapping
    public String addFlight(FlightDto flightDto) {

        return "redirect:/admin";
    }
}
