package ru.kpfu.itis.transportprojectimpl.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportprojectapi.dto.CityDto;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.service.CityService;
import ru.kpfu.itis.transportprojectimpl.entity.CityEntity;
import ru.kpfu.itis.transportprojectimpl.entity.FlightEntity;
import ru.kpfu.itis.transportprojectimpl.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService<CityDto, Long> {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<CityDto> findAll() {
        List<CityEntity> allCities = cityRepository.findAll();
        List<CityDto> cityDtos = new ArrayList<>();
        for (CityEntity city : allCities) {
            cityDtos.add(modelMapper.map(city, CityDto.class));
        }
        return cityDtos;
    }

    @Override
    public Optional<CityDto> findByName(String name) {
        Optional<CityEntity> cityOptional = cityRepository.findByCity(name);
        return cityOptional.map(cityEntity -> Optional.of(modelMapper.map(cityEntity, CityDto.class))).orElse(null);
    }
}


