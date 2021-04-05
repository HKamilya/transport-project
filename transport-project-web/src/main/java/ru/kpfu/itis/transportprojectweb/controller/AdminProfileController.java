package ru.kpfu.itis.transportprojectweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminProfileController {
    @GetMapping("/admin/adminProfile")
    public String getAdminProfile() {
        return "adminProfilePage";
    }

    @GetMapping("/admin/flights")
    public String getFlightsPage() {
        return "flightsPage";
    }
}
