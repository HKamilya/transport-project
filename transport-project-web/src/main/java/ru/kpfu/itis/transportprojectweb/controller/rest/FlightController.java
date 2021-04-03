package ru.kpfu.itis.transportprojectweb.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.service.FlightService;

import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping
    @ResponseBody
    public FlightDto saveFlight(@RequestBody FlightDto flightDto) {
        return (FlightDto) flightService.save(flightDto);
    }

    @GetMapping
    public Page<FlightDto> findAllFlights(Pageable pageable) {
        return flightService.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Optional<FlightDto> getFlightById(@PathVariable Long id) {
        return flightService.findById(id);
    }

    @PutMapping("/{id}l")
    public void updateFlight(@PathVariable Long id, @RequestBody FlightDto flightDto) {
        flightService.save(flightDto);
    }
}
