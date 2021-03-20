package ru.kpfu.itis.transportprojectimpl.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportprojectapi.dto.UserDto;
import ru.kpfu.itis.transportprojectapi.service.UserService;
import ru.kpfu.itis.transportprojectimpl.entity.UserEntity;
import ru.kpfu.itis.transportprojectimpl.repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class UserServiceImpl implements UserService<UserDto, Long> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(UserDto userDto) {

        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(userDto.getDateOfBirth());
            UserEntity user = new UserEntity();
            modelMapper.map(userDto, user);
            user.setId(null);
            user.setDateOfBirth(date1);
            user.setRole(UserEntity.Role.USER);
            user.setState(UserEntity.State.NOT_CONFIRMED);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepository.save(user);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
