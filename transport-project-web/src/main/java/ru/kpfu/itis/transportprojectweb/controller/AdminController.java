package ru.kpfu.itis.transportprojectweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.dto.UserDto;
import ru.kpfu.itis.transportprojectapi.service.AdminService;
import ru.kpfu.itis.transportprojectapi.service.FlightService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping()
    public String adminSignIn() {
        return "adminSignInPage";
    }

    @GetMapping(value = "/flights")
    public String adminFlights() {
        return "flightsPage";
    }

    @GetMapping(value = "/admins")
    public String admins() {
        return "adminsPage";
    }

    @PostMapping("/addAdmin")
    public String addAdmin(UserDto userDto) {
        adminService.addNewAdmin(userDto);
        return "redirect:/admin/admins";
    }

    @PostMapping("/deleteFlight")
    public String deleteFlight(Long id) {
        adminService.deleteFlight(id);
        return "redirect:/admin/flights";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(Long id) {
        adminService.deleteAdminOrUser(id);
        return "redirect:/admin/admins";
    }

    @PostMapping("/addFlight")
    public String addFlight(FlightDto flightDto) {
        adminService.addNewFlight(flightDto);
        return "redirect:/admin/flights";
    }
}
