package ru.kpfu.itis.transportprojectapi.service;

import ru.kpfu.itis.transportprojectapi.dto.ReservationDto;

import java.util.Date;
import java.util.List;

public interface ReservationService<ReservationDto, Long> {


    boolean save(String[] flights, String userEmail, int countOfPass);


    List<ReservationDto> findAllComingFlights(String email, Date date);

    List<ReservationDto> findAllLastFlights(String email, Date date);

    void deleteById(Long id);
}
