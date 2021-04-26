package ru.kpfu.itis.transportprojectapi.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportprojectapi.dto.CityDto;

import java.util.List;
import java.util.Optional;


public interface CityService<CityDto, Long> {

    public List<CityDto> findAll();

    public Optional<CityDto> findByName(String name);
}
