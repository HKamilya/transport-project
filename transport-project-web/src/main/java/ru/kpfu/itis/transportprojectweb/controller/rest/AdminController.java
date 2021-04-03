package ru.kpfu.itis.transportprojectweb.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.dto.UserDto;
import ru.kpfu.itis.transportprojectapi.service.AdminService;


@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public String adminSignIn() {
        return "adminSignInPage";
    }

    @GetMapping("/admin/adminProfile")
    public String adminPage() {
        return "adminProfile";
    }


    @GetMapping(value = "/admin/flights")
    public String adminFlights() {
        return "flightsPage";
    }

    @GetMapping(value = "/admin/admins")
    public String admins() {
        return "adminsPage";
    }

    @PostMapping("/admins")
    @ResponseBody
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

}
