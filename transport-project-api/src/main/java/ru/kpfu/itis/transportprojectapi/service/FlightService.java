package ru.kpfu.itis.transportprojectapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.dto.FlightForm;
import ru.kpfu.itis.transportprojectapi.dto.FlightsList;
import ru.kpfu.itis.transportprojectapi.dto.SearchForm;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface FlightService<FlightDto, Long> {


    FlightDto save(FlightDto flightDto);

    public FlightDto save(FlightForm flightForm);

    public void delete(Long id);

    public Page<FlightDto> findAll(Pageable pageable);


    public void deleteById(Long id);

    public Optional<FlightDto> findById(Long id);

    public List<FlightsList> findOptimalWayByDistance(SearchForm searchForm);

    Page<FlightDto> findByCity(String city, Pageable pageable);
}
