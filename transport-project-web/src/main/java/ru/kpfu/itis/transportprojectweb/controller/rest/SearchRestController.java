package ru.kpfu.itis.transportprojectweb.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.dto.SearchForm;
import ru.kpfu.itis.transportprojectapi.service.FlightService;
import ru.kpfu.itis.transportprojectimpl.entity.FlightEntity;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchRestController {
    @Autowired
    private FlightService flightService;


    @PostMapping
    @ResponseBody
    public ResponseEntity<List<FlightDto>> searchFlight(@RequestBody SearchForm searchForm) {
        List<FlightDto> list = flightService.findOptimalWayByDistance(searchForm);
        System.out.println(list);
        return ResponseEntity.ok(list);
    }
}
