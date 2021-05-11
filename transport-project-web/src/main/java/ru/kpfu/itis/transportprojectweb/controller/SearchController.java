package ru.kpfu.itis.transportprojectweb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.dto.FlightsList;
import ru.kpfu.itis.transportprojectapi.dto.SearchForm;
import ru.kpfu.itis.transportprojectapi.service.CityService;
import ru.kpfu.itis.transportprojectapi.service.FlightService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private CityService cityService;

    @GetMapping("")
    public String getSearchPage(@AuthenticationPrincipal UserDetails userDetails, Model model) throws JsonProcessingException {
        String role = "";
        if (userDetails != null) {
            role = userDetails.getAuthorities().stream().findFirst().toString();
            if (role.contains("ADMIN")) {
                role = "admin";
            }
            model.addAttribute("username", userDetails.getUsername());
        }
        List<String> cities = cityService.findAllCities();
        ObjectMapper objectMapper = new ObjectMapper();
        model.addAttribute("role", role);


        String json = objectMapper.writeValueAsString(cities);
        model.addAttribute("cities", json);
        return "searchPage";
    }


    @PostMapping
    @ResponseBody
    public ResponseEntity<List<FlightsList>> search(@RequestBody SearchForm searchForm) {
        List<FlightsList> list = flightService.findOptimalWayByDistance(searchForm);
        if (searchForm.getSort() == 1) {
            list.sort(new Comparator<FlightsList>() {
                @Override
                public int compare(FlightsList z1, FlightsList z2) {
                    return Double.compare(z1.getDistance(), z2.getDistance());
                }
            });

        }
        if (searchForm.getSort() == 2) {
            list.sort(new Comparator<FlightsList>() {
                @Override
                public int compare(FlightsList z1, FlightsList z2) {
                    return Double.compare(z1.getPrice(), z2.getPrice());
                }
            });

        }
        if (searchForm.getSort() == 3) {
            list.sort(new Comparator<FlightsList>() {
                @Override
                public int compare(FlightsList z1, FlightsList z2) {
                    return Double.compare(z1.getTime(), z2.getTime());
                }
            });

        }
        return ResponseEntity.ok(list);
    }
}
