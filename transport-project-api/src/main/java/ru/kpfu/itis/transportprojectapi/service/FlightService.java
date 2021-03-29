package ru.kpfu.itis.transportprojectapi.service;

import ru.kpfu.itis.transportprojectapi.dto.SearchForm;

import java.util.List;

public interface FlightService<FlightDto, Long> {

    public FlightDto save(FlightDto flightDto);

    public void delete(Long id);


    public List<FlightDto> search(SearchForm searchForm);
}
