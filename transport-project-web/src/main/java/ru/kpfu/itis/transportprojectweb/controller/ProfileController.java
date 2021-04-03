package ru.kpfu.itis.transportprojectweb.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.transportprojectweb.security.details.UserDetailsImpl;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profilePage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        if (userDetails.getRole().equals("ADMIN")) {
            return "adminProfile";
        } else {
            return "profilePage";
        }
    }
}
