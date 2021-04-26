package ru.kpfu.itis.transportprojectimpl.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.dto.ReservationDto;
import ru.kpfu.itis.transportprojectapi.dto.UserDto;
import ru.kpfu.itis.transportprojectapi.service.FlightService;
import ru.kpfu.itis.transportprojectapi.service.ReservationService;
import ru.kpfu.itis.transportprojectapi.service.UserService;
import ru.kpfu.itis.transportprojectimpl.entity.FlightEntity;
import ru.kpfu.itis.transportprojectimpl.entity.ReservationEntity;
import ru.kpfu.itis.transportprojectimpl.entity.UserEntity;
import ru.kpfu.itis.transportprojectimpl.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService<ReservationDto, Long> {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private FlightService flightService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean save(String[] flights, String userEmail, int countOfPass) {
        ReservationEntity reservationEntity = new ReservationEntity();
        UserDto userDto = userService.findByEmail(userEmail);
        reservationEntity.setPassenger(modelMapper.map(userDto, UserEntity.class));
        reservationEntity.setCountOfPlaces(countOfPass);
        for (String flight : flights) {
            Optional<FlightDto> flightDto = flightService.findById(Long.valueOf(flight));
            System.out.println(reservationEntity);
            reservationEntity.setFlight(modelMapper.map(flightDto.get(), FlightEntity.class));
            ReservationEntity save = reservationRepository.save(reservationEntity);
            return true;
        }
        return false;
    }

    @Override
    public List<ReservationDto> findAllComingFlights(String email) {
        UserDto userDto = userService.findByEmail(email);
        List<ReservationDto> reservationDtos = new ArrayList<>();
        System.out.println("stop");
        List<ReservationEntity> reservationEntities = reservationRepository
                .findAll(SpecificationUtils
                        .byPassengerId(userDto.getId())
                        .and((root, criteriaQuery, criteriaBuilder) -> {
                            root.fetch("flight").fetch("cityTo");
                            root.fetch("flight").fetch("cityFrom");
                            return null;
                        }));
        for (ReservationEntity reservation : reservationEntities) {
            reservationDtos.add(modelMapper.map(reservation, ReservationDto.class));
        }
        return reservationDtos;
    }

    public static class SpecificationUtils {
        public static Specification<ReservationEntity> byId(Long id) {
            return ((root, criteriaQuery, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("id"), id);
            });
        }

        public static Specification<ReservationEntity> byPassengerId(Long id) {
            return ((root, criteriaQuery, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("passenger"), id);
            });
        }


    }
}
