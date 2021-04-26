package ru.kpfu.itis.transportprojectweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.dto.SearchForm;
import ru.kpfu.itis.transportprojectapi.service.FlightService;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private FlightService flightService;

    @GetMapping("")
    public String getSearchPage() {
        return "searchPage";
    }


    @PostMapping
    @ResponseBody
    public ResponseEntity<List<FlightDto>> search(@RequestBody SearchForm searchForm) {
        List<FlightDto> list = flightService.findOptimalWayByDistance(searchForm);
        return ResponseEntity.ok(list);
    }
}
