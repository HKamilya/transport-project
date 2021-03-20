package ru.kpfu.itis.transportprojectapi.service;

public interface FlightService<FlightDto, Long> {

    public void save(FlightDto flightDto);

    public void delete(Long id);
}
