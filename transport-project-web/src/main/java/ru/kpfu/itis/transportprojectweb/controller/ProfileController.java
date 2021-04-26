package ru.kpfu.itis.transportprojectweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.transportprojectapi.dto.ReservationDto;
import ru.kpfu.itis.transportprojectapi.service.ReservationService;


import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/profile")
    public String profilePage(@AuthenticationPrincipal UserDetails userDetails,
                              Model model) {
        String email = "";
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
            email = userDetails.getUsername();
        }
        List<ReservationDto> reservations = reservationService.findAllComingFlights(email);
        System.out.println(reservations);
        model.addAttribute("comingFlights", reservations);
        return "profilePage";
    }


}
