package ru.kpfu.itis.transportprojectweb.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.dto.FlightsList;
import ru.kpfu.itis.transportprojectapi.dto.SearchForm;
import ru.kpfu.itis.transportprojectapi.service.FlightService;
import ru.kpfu.itis.transportprojectimpl.entity.FlightEntity;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchRestController {
    @Autowired
    private FlightService flightService;


    @PostMapping
    @ResponseBody
    public ResponseEntity<List<FlightsList>> searchFlight(@RequestBody SearchForm searchForm) {
        List<FlightsList> list = flightService.findOptimalWayByDistance(searchForm);
        System.out.println(list);
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
        return ResponseEntity.ok(list);
    }
}
