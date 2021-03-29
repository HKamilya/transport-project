package ru.kpfu.itis.transportprojectapi.service;

import ru.kpfu.itis.transportprojectapi.dto.FlightDto;

public interface AdminService<UserDto, Long, FlightDto> {

    public void addNewAdmin(UserDto userDto);

    public void deleteAdminOrUser(Long id);

    public void deleteFlight(Long id);

    public void changeFlightData(FlightDto flightDto);
}
