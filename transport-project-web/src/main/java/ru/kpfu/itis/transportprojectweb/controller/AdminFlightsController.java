package ru.kpfu.itis.transportprojectweb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportprojectapi.dto.CityDto;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.dto.FlightForm;
import ru.kpfu.itis.transportprojectapi.service.CityService;
import ru.kpfu.itis.transportprojectapi.service.FlightService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminFlightsController {
    @Autowired
    private CityService cityService;
    @Autowired
    private FlightService flightService;

    @GetMapping("/admin/cities")
    @ResponseBody
    public ResponseEntity getFlightsPage() throws JsonProcessingException {
        List<CityDto> list = cityService.findAll();
        List<String> cities = new ArrayList<>();
        for (CityDto city : list) {
            cities.add(city.getCity());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cities);
        System.out.println(json);
        return ResponseEntity.ok(json);
    }

    @GetMapping("/admin/flights/get")
    public String getAdminFlights(Model model,
                                  @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<FlightDto> flightDtos = flightService.findAll(pageable);
        model.addAttribute("page", flightDtos);
        System.out.println(flightDtos.getTotalElements());
        model.addAttribute("url", "/admin/flights/get");
        return "flightsPage";
    }

    @PostMapping("/admin/flights")
    @ResponseBody
    public FlightDto saveFlights(@RequestBody FlightForm form) {
        return (FlightDto) flightService.save(form);
    }

    @GetMapping("/admin/flights/update/{id}")
    public String getFlightUpdatePage(@PathVariable("id") Long id, Model model) {
        Optional<FlightDto> flightDto = flightService.findById(id);
        System.out.println(flightDto.get().getPrice());
        model.addAttribute("flight", flightDto.get());
        model.addAttribute("price", String.valueOf(flightDto.get().getPrice()).replace(" ", ""));
        return "updateFlightPage";
    }

    @PostMapping("/admin/flights/update/{id}")
    public String getFlightUpdatePage(FlightForm form, Model model) {
        System.out.println(form);
        flightService.save(form);
        return "redirect:/admin/flights/get";
    }

}
