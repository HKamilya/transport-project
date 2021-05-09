package ru.kpfu.itis.transportprojectweb.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.transportprojectapi.service.CityService;
import ru.kpfu.itis.transportprojectapi.service.FlightService;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private CityService cityService;

    @GetMapping("")
    public String getMainPage(@AuthenticationPrincipal UserDetails userDetails, Model model) throws JsonProcessingException {
//
//        if (userDetails != null) {
//            model.addAttribute("username", userDetails.getUsername());
//            if (userDetails.getAuthorities().stream().findFirst().toString().equals("ADMIN"))
//                model.addAttribute("role", "admin");
//        }
//        List<String> cities = cityService.findAllCities();
//        ObjectMapper objectMapper = new ObjectMapper();
//
//
//        String json = objectMapper.writeValueAsString(cities);
//        model.addAttribute("cities", json);
//        return "searchPage";
        return "redirect:/search";
    }

}
