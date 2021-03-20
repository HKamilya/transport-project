package ru.kpfu.itis.transportprojectimpl.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.service.FlightService;
import ru.kpfu.itis.transportprojectimpl.entity.FlightEntity;
import ru.kpfu.itis.transportprojectimpl.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService<FlightDto, Long> {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(FlightDto flightDto) {
        FlightEntity flightEntity = new FlightEntity();
        modelMapper.map(flightDto, flightEntity);
        flightEntity.setId(null);
        flightRepository.save(flightEntity);
    }

    @Override
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }
}
