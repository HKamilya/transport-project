package ru.kpfu.itis.transportprojectweb.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.dto.FlightForm;
import ru.kpfu.itis.transportprojectapi.service.FlightService;

import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping
    @ResponseBody
    public FlightDto saveFlight(@RequestBody FlightForm form) {
        return (FlightDto) flightService.save(form);
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
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Long id) {
        Optional<FlightDto> flightDto = flightService.findById(id);
        return flightDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PutMapping("/{id}l")
    public void updateFlight(@PathVariable Long id, @RequestBody FlightForm flightDto) {
        flightService.save(flightDto);
    }
}
