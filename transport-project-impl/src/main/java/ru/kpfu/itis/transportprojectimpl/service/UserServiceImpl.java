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
import java.util.Optional;


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
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepository.save(user);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(UserDto userDto) {
        userRepository.save(modelMapper.map(userDto, UserEntity.class));
    }

    @Override
    public UserDto findByEmail(String email) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        return optionalUser.map(userEntity -> modelMapper.map(userEntity, UserDto.class)).orElse(null);
    }

    @Override
    public void signUpAfterOAuth(String email, String name, String lastname, String provider) {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setFirstname(name);
        user.setLastname(lastname);
        user.setRole(UserEntity.Role.USER);
        user.setAuth_provider(UserEntity.AuthProvider.GOOGLE);
        userRepository.save(user);
    }

    @Override
    public void updateUserAfterOAuth(UserDto userDto, String name, String provider) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        if (provider.equals(UserEntity.AuthProvider.GOOGLE.toString())) {
            userEntity.setAuth_provider(UserEntity.AuthProvider.GOOGLE);
        } else {
            userEntity.setAuth_provider(UserEntity.AuthProvider.LOCAL);
        }
        userEntity.setFirstname(name);
        userRepository.save(userEntity);
    }
}
