package ru.kpfu.itis.transportprojectapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.kpfu.itis.transportprojectapi.dto.FlightForm;
import ru.kpfu.itis.transportprojectapi.dto.SearchForm;

import java.util.List;
import java.util.Optional;

public interface FlightService<FlightDto, Long> {

    void temp();

    public FlightDto save(FlightForm flightForm);

    public void delete(Long id);

    public Page<FlightDto> findAll(Pageable pageable);


    public List<FlightDto> search(SearchForm searchForm);


    public void deleteById(Long id);

    public Optional<FlightDto> findById(Long id);

    public List<List<FlightDto>> findOptimalWayByDistance(SearchForm searchForm);
}
