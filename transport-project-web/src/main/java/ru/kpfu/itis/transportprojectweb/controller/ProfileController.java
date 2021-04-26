package ru.kpfu.itis.transportprojectweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.transportprojectapi.dto.ReservationDto;
import ru.kpfu.itis.transportprojectapi.service.ReservationService;
import ru.kpfu.itis.transportprojectweb.security.details.UserDetailsImpl;
import ru.kpfu.itis.transportprojectweb.security.oauth.CustomOAuth2User;

import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/profile")
    public String profilePage(@AuthenticationPrincipal UserDetailsImpl userDetails,
                              @AuthenticationPrincipal CustomOAuth2User oAuth2User,
                              Model model) {
        String email = "";
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
            email = userDetails.getEmail();
        }
        if (oAuth2User != null) {
            model.addAttribute("username", oAuth2User.getName() + " " + oAuth2User.getLastname());
            email = oAuth2User.getEmail();
        }
        List<ReservationDto> reservations = reservationService.findAllComingFlights(email);
        System.out.println(reservations);
        model.addAttribute("comingFlights", reservations);
        return "profilePage";
    }


}
