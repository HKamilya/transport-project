package ru.kpfu.itis.transportprojectweb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.transportprojectapi.dto.CityDto;
import ru.kpfu.itis.transportprojectapi.service.CityService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminCitiesController {
    @Autowired
    private CityService cityService;

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
}
