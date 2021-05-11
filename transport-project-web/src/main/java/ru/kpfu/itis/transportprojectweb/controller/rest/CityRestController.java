package ru.kpfu.itis.transportprojectweb.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportprojectapi.dto.CityDto;
import ru.kpfu.itis.transportprojectapi.service.CityService;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityRestController {
    @Autowired
    private CityService cityService;

    @GetMapping
    public List<String> getAllCities() {
        List<String> cities = cityService.findAllCities();
        return cities;
    }

    @PostMapping
    public ResponseEntity<Object> postCity(@RequestBody CityDto cityDto) {
        return ResponseEntity.ok(cityService.save(cityDto));
    }
}
