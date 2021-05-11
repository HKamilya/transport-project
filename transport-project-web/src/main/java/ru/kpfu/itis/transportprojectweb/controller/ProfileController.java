package ru.kpfu.itis.transportprojectweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.transportprojectapi.dto.ReservationDto;
import ru.kpfu.itis.transportprojectapi.service.ReservationService;
import ru.kpfu.itis.transportprojectimpl.aspect.CacheAspect;


import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public String profilePage(@AuthenticationPrincipal UserDetails userDetails,
                              Model model) throws ParseException {
        String email = "";
        System.out.println(userDetails.getAuthorities());
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
            email = userDetails.getUsername();
        }
        List<ReservationDto> reservations = reservationService.findAllComingFlights(email, new Date());
        List<ReservationDto> reservations2 = reservationService.findAllLastFlights(email, new Date());
        model.addAttribute("comingFlights", reservations);
        model.addAttribute("lastFlights", reservations2);
        return "profilePage";
    }

    @PostMapping
    public String postProfilePage(@RequestParam("id") Long id) {
        reservationService.deleteById(id);
        return "redirect:/profile";
    }


}
