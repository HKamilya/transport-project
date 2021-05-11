package ru.kpfu.itis.transportprojectweb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportprojectapi.dto.CityDto;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.dto.FlightForm;
import ru.kpfu.itis.transportprojectapi.dto.PlaneDto;
import ru.kpfu.itis.transportprojectapi.service.CityService;
import ru.kpfu.itis.transportprojectapi.service.FlightService;
import ru.kpfu.itis.transportprojectapi.service.PlaneService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminFlightsController {
    @Autowired
    private CityService cityService;
    @Autowired
    private FlightService flightService;
    @Autowired
    private PlaneService planeService;


    @GetMapping("/admin/flights/get")
    public String getAdminFlights(@AuthenticationPrincipal UserDetails userDetails,
                                  @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) throws JsonProcessingException {
        Page<FlightDto> flightDtos = flightService.findAll(pageable);
        List<String> cities = cityService.findAllCities();
        ObjectMapper objectMapper = new ObjectMapper();


        String json = objectMapper.writeValueAsString(cities);
        model.addAttribute("role", "admin");
        model.addAttribute("cities", json);
        model.addAttribute("page", flightDtos);
        model.addAttribute("username", userDetails.getUsername());
        List<PlaneDto> planes = planeService.findAll();
        model.addAttribute("planes", planes);
        model.addAttribute("url", "/admin/flights/get");
        return "flightsPage";
    }

    @PostMapping("/admin/flights")
    @ResponseBody
    public FlightDto saveFlights(@RequestBody FlightForm form) {
        return (FlightDto) flightService.save(form);
    }

    @GetMapping("/admin/flights/update/{id}")
    public String getFlightUpdatePage(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails, Model model) throws JsonProcessingException {
        model.addAttribute("username", userDetails.getUsername());
        Optional<FlightDto> flightDto = flightService.findById(id);
        model.addAttribute("flight", flightDto.get());
        List<PlaneDto> planes = planeService.findAll();
        List<String> cities = cityService.findAllCities();
        ObjectMapper objectMapper = new ObjectMapper();
        model.addAttribute("role", "admin");


        String json = objectMapper.writeValueAsString(cities);
        model.addAttribute("cities", json);
        model.addAttribute("planes", planes);
        model.addAttribute("price", String.valueOf(flightDto.get().getPrice()).replace(" ", ""));
        return "updateFlightPage";
    }

    @PostMapping("/admin/flights/update/{id}")
    public String getFlightUpdatePage(FlightForm form, Model model) {
        flightService.save(form);
        return "redirect:/admin/flights/get";
    }

    @GetMapping("/admin/flights/delete/{id}")
    public String getFlightUpdatePage(@PathVariable("id") Long id, Model model) {
        flightService.deleteById(id);
        model.addAttribute("role", "admin");
        return "redirect:/admin/flights/get";
    }

    @MessageMapping("/changeFlight")
    @SendTo("/topic/schedule")
    public FlightDto change(FlightForm flightForm) {
        return (FlightDto) flightService.save(flightForm);
    }
}
