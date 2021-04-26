package ru.kpfu.itis.transportprojectweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.transportprojectapi.dto.ReservationDto;
import ru.kpfu.itis.transportprojectapi.service.ReservationService;
import ru.kpfu.itis.transportprojectweb.security.details.UserDetailsImpl;
import ru.kpfu.itis.transportprojectweb.security.oauth.CustomOAuth2User;

@Controller
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/reserve")
    public String reserveFlight(@AuthenticationPrincipal UserDetailsImpl userDetails, @AuthenticationPrincipal CustomOAuth2User oAuth2User, @RequestParam("ids") String ids, @RequestParam("count") int count, Model model) {
        String userEmail = "";
        if (userDetails != null) {
            userEmail = userDetails.getEmail();
        }
        if (oAuth2User != null) {
            userEmail = oAuth2User.getEmail();
        }
        ids = ids.trim();
        String[] flight = ids.split(" ");
        reservationService.save(flight, userEmail, count);
        return "redirect:/search";

    }
}
