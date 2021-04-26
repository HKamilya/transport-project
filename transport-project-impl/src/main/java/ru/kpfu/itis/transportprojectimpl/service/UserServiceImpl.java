package ru.kpfu.itis.transportprojectimpl.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportprojectapi.dto.UserDto;
import ru.kpfu.itis.transportprojectapi.service.UserService;
import ru.kpfu.itis.transportprojectimpl.annotation.LogExecutionTime;
import ru.kpfu.itis.transportprojectimpl.entity.ReservationEntity;
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
    @LogExecutionTime
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
    @LogExecutionTime
    public void save(UserDto userDto) {
        userRepository.save(modelMapper.map(userDto, UserEntity.class));
    }

    @Override
    @LogExecutionTime
    public UserDto findByEmail(String email) {
        Optional<UserEntity> optionalUser = userRepository.findOne(SpecificationUtils.byEmail(email)
                .and((root, criteriaQuery, criteriaBuilder) -> {
                    root.fetch("reservations").fetch("flight").fetch("cityTo").getParent().fetch("cityFrom");
                    return null;
                }));
        return optionalUser.map(userEntity -> modelMapper.map(userEntity, UserDto.class)).orElse(null);
    }

    @Override
    @LogExecutionTime
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
    @LogExecutionTime
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

    @Override
    @LogExecutionTime
    public UserDto findByEmailOrUsername(String email) {
        Optional<UserEntity> optionalUser = userRepository.findByEmailOrUsername(email, email);
        Optional<UserEntity> optionalUser2 = userRepository.findOne(SpecificationUtils.byEmail(email)
                .or(SpecificationUtils.byUsername(email))
                .and((root, criteriaQuery, criteriaBuilder) -> {
                    root.fetch("reservations").fetch("flight").fetch("cityTo").getParent().fetch("cityFrom");
                    return null;
                }));
        return optionalUser.map(userEntity -> modelMapper.map(userEntity, UserDto.class)).orElse(null);

    }

    @Override
    public Optional<UserDto> findByUsername(String username) {
        Optional<UserEntity> optionalUser2 = userRepository.findOne(SpecificationUtils.byUsername(username)
                .or(SpecificationUtils.byUsername(username))
                .and((root, criteriaQuery, criteriaBuilder) -> {
                    root.fetch("reservations").fetch("flight").fetch("cityTo").getParent().fetch("cityFrom");
                    return null;
                }));
        return Optional.of(modelMapper.map(optionalUser2.get(), UserDto.class));
    }

    public static class SpecificationUtils {
        public static Specification<UserEntity> byId(Long id) {
            return ((root, criteriaQuery, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("id"), id);
            });
        }

        public static Specification<UserEntity> byEmail(String email) {
            return ((root, criteriaQuery, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("email"), email);
            });
        }

        public static Specification<UserEntity> byUsername(String username) {
            return ((root, criteriaQuery, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("username"), username);
            });
        }

        public static Specification<UserEntity> byPassengerId(Long id) {
            return ((root, criteriaQuery, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("passenger"), id);
            });
        }


    }
}
