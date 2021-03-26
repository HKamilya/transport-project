package ru.kpfu.itis.transportprojectimpl.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.dto.UserDto;
import ru.kpfu.itis.transportprojectapi.service.AdminService;
import ru.kpfu.itis.transportprojectapi.service.FlightService;
import ru.kpfu.itis.transportprojectimpl.entity.FlightEntity;
import ru.kpfu.itis.transportprojectimpl.entity.UserEntity;
import ru.kpfu.itis.transportprojectimpl.repository.UserRepository;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AdminServiceImpl implements AdminService<UserDto, Long, FlightDto> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FlightService flightService;

    @Override
    public void addNewAdmin(UserDto userDto) {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(userDto.getDateOfBirth());
            UserEntity user = new UserEntity();
            modelMapper.map(userDto, user);
            user.setId(null);
            user.setDateOfBirth(date1);
            user.setRole(UserEntity.Role.ADMIN);
            user.setState(UserEntity.State.CONFIRMED);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepository.save(user);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNewFlight(FlightDto flightDto) {
        flightService.save(flightDto);
    }

    @Override
    public void deleteAdminOrUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteFlight(Long id) {
        flightService.delete(id);
    }

    @Override
    public void changeFlightData(FlightDto flightDto) {

    }
}
