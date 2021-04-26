package ru.kpfu.itis.transportprojectweb.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.dto.FlightForm;
import ru.kpfu.itis.transportprojectapi.dto.ReservationDto;
import ru.kpfu.itis.transportprojectapi.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/flights/reservations")
public class ReservationRestController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/{email}")
    public List<FlightDto> findAllComingFlights(@PathVariable String email, Pageable pageable) {
        return (List<FlightDto>) reservationService.findAllComingFlights(email);
    }

    @PostMapping
    @ResponseBody
    public boolean saveReservation(@RequestParam("ids") String ids, @RequestParam("count") int count, @RequestParam("email") String email) {
        ids = ids.trim();
        String[] flight = ids.split(" ");
        return reservationService.save(flight, email, count);
    }
}
