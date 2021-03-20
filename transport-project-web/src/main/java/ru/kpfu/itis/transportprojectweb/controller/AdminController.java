package ru.kpfu.itis.transportprojectweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.dto.UserDto;
import ru.kpfu.itis.transportprojectapi.service.AdminService;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public String adminSignIn() {
        return "adminSignInPage";
    }

    @GetMapping(value = "/flights")
    public String adminFlights() {
        return "flightsPage";
    }

    @GetMapping(value = "/admins")
    public String admins() {
        return "adminsPage.html";
    }

    @PostMapping("/addAdmin")
    public String addAdmin(UserDto userDto) {
        adminService.addNewAdmin(userDto);
        return "redirect:/admins";
    }

    @PostMapping("/addFlight")
    public String addAdmin(FlightDto flightDto) {

        return "redirect:/admins";
    }
}
