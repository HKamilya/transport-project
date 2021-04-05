package ru.kpfu.itis.transportprojectweb.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profilePage(@AuthenticationPrincipal UserDetails userDetails, @AuthenticationPrincipal OAuth2User oAuth2User, Model model) {
        return "profilePage";
    }
}
