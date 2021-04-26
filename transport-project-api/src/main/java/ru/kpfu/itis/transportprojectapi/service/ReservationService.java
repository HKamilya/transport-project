package ru.kpfu.itis.transportprojectapi.service;

import ru.kpfu.itis.transportprojectapi.dto.ReservationDto;

import java.util.List;

public interface ReservationService<ReservationDto, Long> {


    boolean save(String[] flights, String userEmail, int countOfPass);

    List<ru.kpfu.itis.transportprojectapi.dto.ReservationDto> findAllComingFlights(String email);
}
