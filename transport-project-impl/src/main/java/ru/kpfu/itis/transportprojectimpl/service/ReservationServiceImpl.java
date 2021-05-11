package ru.kpfu.itis.transportprojectimpl.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.dto.ReservationDto;
import ru.kpfu.itis.transportprojectapi.dto.UserDto;
import ru.kpfu.itis.transportprojectapi.service.FlightService;
import ru.kpfu.itis.transportprojectapi.service.ReservationService;
import ru.kpfu.itis.transportprojectapi.service.UserService;
import ru.kpfu.itis.transportprojectimpl.aspect.Cacheable;
import ru.kpfu.itis.transportprojectimpl.entity.CityEntity;
import ru.kpfu.itis.transportprojectimpl.entity.FlightEntity;
import ru.kpfu.itis.transportprojectimpl.entity.ReservationEntity;
import ru.kpfu.itis.transportprojectimpl.entity.UserEntity;
import ru.kpfu.itis.transportprojectimpl.repository.ReservationRepository;

import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        Optional<UserDto> userDto = userService.findByEmail(userEmail);
        for (String flight : flights) {
            System.out.println(flight);
            Optional<FlightDto> flightDto = flightService.findById(Long.valueOf(flight));
            if (flightDto.isPresent()) {
                ReservationEntity reservationEntity = new ReservationEntity();
                reservationEntity.setPassenger(modelMapper.map(userDto.get(), UserEntity.class));
                reservationEntity.setCountOfPlaces(countOfPass);
                flightDto.get().setCountOfPlaces(flightDto.get().getCountOfPlaces() - countOfPass);
                flightService.save(flightDto.get());
                reservationEntity.setFlight(modelMapper.map(flightDto.get(), FlightEntity.class));
                ReservationEntity save = reservationRepository.save(reservationEntity);
            }
        }
        return true;
    }

    @Override
    public List<ReservationDto> findAllComingFlights(String email, Date date) {
        Optional<UserDto> userDto = userService.findByEmail(email);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = dateFormat.format(date);
        if (userDto.isPresent()) {
            List<ReservationDto> reservationDtos = new ArrayList<>();
            List<ReservationEntity> reservationEntities = reservationRepository
                    .findAll(SpecificationUtils
                            .byPassengerId(userDto.get().getId())
                            .and(SpecificationUtils.byDateGreat(date))
                            .and((root, criteriaQuery, criteriaBuilder) -> {
                                root.fetch("flight").fetch("cityTo").getParent().fetch("cityFrom").getParent().fetch("planeType");
                                root.fetch("passenger");
                                return null;
                            }));
            for (ReservationEntity reservation : reservationEntities) {
                reservationDtos.add(modelMapper.map(reservation, ReservationDto.class));
            }
            return reservationDtos;
        }
        return new ArrayList<>();
    }


    @Override
    public List<ReservationDto> findAllLastFlights(String email, Date date) {
        Optional<UserDto> userDto = userService.findByEmail(email);
        if (userDto.isPresent()) {
            List<ReservationDto> reservationDtos = new ArrayList<>();
            List<ReservationEntity> reservationEntities = reservationRepository
                    .findAll(SpecificationUtils.byPassengerId(userDto.get().getId())
                            .and(SpecificationUtils.byDateLess(date))
                            .and((root, criteriaQuery, criteriaBuilder) -> {
                                root.fetch("flight").fetch("cityTo").getParent().fetch("cityFrom").getParent().fetch("planeType");
                                root.fetch("passenger");
                                return null;
                            }));
            for (ReservationEntity reservation : reservationEntities) {
                reservationDtos.add(modelMapper.map(reservation, ReservationDto.class));
            }
            return reservationDtos;
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteById(Long id) {
        Optional<ReservationEntity> reservationEntity = reservationRepository
                .findOne(SpecificationUtils.byId(id)
                        .and((root, criteriaQuery, criteriaBuilder) -> {
                            root.fetch("flight").fetch("cityTo").getParent().fetch("cityFrom").getParent().fetch("planeType");
                            root.fetch("passenger");
                            return null;
                        }));
        if (reservationEntity.isPresent()) {
            FlightEntity flightEntity = reservationEntity.get().getFlight();
            flightEntity.setCountOfPlaces(flightEntity.getCountOfPlaces() + reservationEntity.get().getCountOfPlaces());
            flightService.save(modelMapper.map(flightEntity, FlightDto.class));
            reservationRepository.deleteById(id);
        }
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

        public static Specification<ReservationEntity> byDateLess(Date date) {
            return new Specification<ReservationEntity>() {
                @Override
                public Predicate toPredicate(Root<ReservationEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Join<ReservationEntity, FlightEntity> cityEntityListJoin = root.join("flight", JoinType.LEFT);
                    Predicate predicate = criteriaBuilder.lessThanOrEqualTo(cityEntityListJoin.get("dateTimeDep"), date);
                    return predicate;
                }
            };
        }

        public static Specification<ReservationEntity> byDateGreat(Date date) {
            return new Specification<ReservationEntity>() {
                @Override
                public Predicate toPredicate(Root<ReservationEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Join<ReservationEntity, FlightEntity> cityEntityListJoin = root.join("flight", JoinType.LEFT);
                    Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(cityEntityListJoin.get("dateTimeDep"), date);
                    return predicate;
                }
            };
        }

    }
}
