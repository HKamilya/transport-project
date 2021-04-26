package ru.kpfu.itis.transportprojectimpl.service;

import com.google.maps.errors.ApiException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.dto.FlightForm;
import ru.kpfu.itis.transportprojectapi.dto.SearchForm;
import ru.kpfu.itis.transportprojectapi.service.CityService;
import ru.kpfu.itis.transportprojectapi.service.FlightService;
import ru.kpfu.itis.transportprojectimpl.entity.CityEntity;
import ru.kpfu.itis.transportprojectimpl.entity.FlightEntity;
import ru.kpfu.itis.transportprojectimpl.repository.FlightRepository;
import ru.kpfu.itis.transportprojectimpl.util.JsonReader;

import javax.persistence.criteria.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static java.lang.Math.*;

@Service
public class FlightServiceImpl implements FlightService<FlightDto, Long> {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CityService cityService;


    @Override
    public void temp() {
        Optional<FlightEntity> flightEntity = flightRepository.findById(8L);
        System.out.println(flightEntity.get().reservations);
    }

    @Override
    public FlightDto save(FlightForm form) {
        Date dateTimeDep = null;
        Date dateArr = null;
        JsonReader jsonReader = new JsonReader();
        try {
            double[] to = jsonReader.main(form.getCityTo(), form.getCountryTo());
            double[] from = jsonReader.main(form.getCityFrom(), form.getCountryFrom());
            System.out.println();
            double distance = distance(to[0], from[0], to[1], from[1]);
            System.out.println(distance);
            double duration = Math.abs(distance) / 840 * 60 * 60 * 1000;
            long second = (long) ((duration / 1000) % 60);
            long minute = (long) ((duration / (1000 * 60)) % 60);
            long hour = (long) ((duration / (1000 * 60 * 60)) % 24);

            String time = String.format("%02d:%02d:%02d", hour, minute, second);
            System.out.println(time);
            dateTimeDep = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(form.getDateTimeDep().replace('T', ' ') + ":00");
            DateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date date = format.parse(time);
            long time1 = dateTimeDep.getTime();
            System.out.println(date);
            long time2;
            System.out.println(duration);
            time2 = (long) (time1 + duration);
            dateArr = dateTimeDep;
            dateArr.setTime(time2);
            System.out.println(dateArr);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            dateArr = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(df.format(dateArr));
            dateTimeDep = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(form.getDateTimeDep().replace('T', ' '));
            System.out.println(dateTimeDep);
            System.out.println(dateArr);

            FlightEntity flightEntity = new FlightEntity();
            modelMapper.map(form, flightEntity);
            flightEntity.setCityTo(modelMapper.map(cityService.findByName(form.getCityTo()).get(), CityEntity.class));
            flightEntity.setCityFrom(modelMapper.map(cityService.findByName(form.getCityFrom()).get(), CityEntity.class));
            flightEntity.setDateTimeArr(dateArr);
            flightEntity.setDateTimeDep(dateTimeDep);
            flightEntity.setDistance(distance);
            if (flightEntity.getId() == null) {
                flightEntity.setState(FlightEntity.State.ACTIVE);
            }
            FlightEntity flightEntity1 = flightRepository.save(flightEntity);
            return modelMapper.map(flightEntity1, FlightDto.class);
        } catch (ParseException | IOException | ApiException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public Page<FlightDto> findAll(Pageable pageable) {
        return flightRepository.findAll(SpecificationUtils.byId(0L)
                .and(((root, criteriaQuery, criteriaBuilder) -> {
                    root.fetch("cityTo");
                    root.fetch("cityFrom");
                    return null;
                })), pageable).map(flightEntity -> modelMapper.map(flightEntity, FlightDto.class));
    }

    @Override
    public List<FlightDto> search(SearchForm searchForm) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(searchForm.getDate() + " 00:00:00");
            List<FlightEntity> flightEntities = flightRepository
                    .findAll(SpecificationUtils
                            .byCityFrom_City(searchForm.getCityFrom())
                            .and(SpecificationUtils.byCityTo_City(searchForm.getCityTo()))
                            .and(SpecificationUtils.byDateTimeDepGreaterThanEqual(date))
                            .and(SpecificationUtils.byCountOfPlacesGreaterThanEqual(searchForm.getCountOfPerson()))
                            .and((root, criteriaQuery, criteriaBuilder) -> {
                                root.fetch("cityTo");
                                root.fetch("cityFrom");
                                return null;
                            }));
            List<FlightDto> flightDtoList = new ArrayList<>();
            for (FlightEntity flight : flightEntities) {
                FlightDto flightDto = new FlightDto();
                flight.setDateTimeDep(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(flight.getDateTimeDep().toString()));
                flight.setDateTimeArr(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(flight.getDateTimeArr().toString()));

                modelMapper.map(flight, flightDto);
                flightDtoList.add(flightDto);
            }

            return flightDtoList;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public Optional<FlightDto> findById(Long id) {
        Optional<FlightEntity> flightOptional = flightRepository.findById(id);
        return flightOptional.map(flightEntity -> Optional.of(modelMapper.map(flightEntity, FlightDto.class))).orElse(null);
    }

    @Override
    public List<List<FlightDto>> findOptimalWayByDistance(SearchForm searchForm) {
        List<List<FlightDto>> flights = new ArrayList<>();
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(searchForm.getDate());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(searchForm.getDate()));
            c.add(Calendar.DATE, 2);
            String dateStr = sdf.format(c.getTime());
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            int k = 0;
            List<FlightDto> flightDtoList = search(searchForm);
            for (int i = 0; i < flightDtoList.size(); i++) {
                k++;
                flights.add(new ArrayList<>());
                flights.get(i).add(flightDtoList.get(i));
            }
            List<FlightEntity> flightEntities = flightRepository
                    .findAll(SpecificationUtils
                            .byCityFrom_City(searchForm.getCityFrom())
                            .and(SpecificationUtils.byDateTimeDepGreaterThanEqual(date))
                            .and(SpecificationUtils.byDateTimeDepLessThanEqual(date1))
                            .and(SpecificationUtils.byCountOfPlacesGreaterThanEqual(searchForm.getCountOfPerson()))
                            .and((root, criteriaQuery, criteriaBuilder) -> {
                                root.fetch("cityTo");
                                root.fetch("cityFrom");
                                return null;
                            }));
            System.out.println(flightEntities);
            for (FlightEntity flightEntity : flightEntities) {
                List<FlightEntity> flightEntities1 = flightRepository
                        .findAll(SpecificationUtils
                                .byCityFrom_City(flightEntity.getCityTo().getCity())
                                .and(SpecificationUtils.byDateTimeDepGreaterThanEqual(flightEntity.getDateTimeArr()))
                                .and(SpecificationUtils.byDateTimeDepLessThanEqual(date))
                                .and(SpecificationUtils.byCountOfPlacesGreaterThanEqual(searchForm.getCountOfPerson()))
                                .and((root, criteriaQuery, criteriaBuilder) -> {
                                    root.fetch("cityTo");
                                    root.fetch("cityFrom");
                                    return null;
                                }));
                for (FlightEntity entity : flightEntities1) {
                    if (entity.getCityTo().getCity().equals(searchForm.getCityTo())) {
                        flights.add(new ArrayList<>());
                        System.out.println(flights.size());
                        flightEntity.setDateTimeDep(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(flightEntity.getDateTimeDep().toString()));
                        flightEntity.setDateTimeArr(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(flightEntity.getDateTimeArr().toString()));
                        flights.get(k).add(modelMapper.map(flightEntity, FlightDto.class));
                        entity.setDateTimeArr(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(entity.getDateTimeArr().toString()));
                        entity.setDateTimeDep(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(entity.getDateTimeDep().toString()));
                        flights.get(k).add(modelMapper.map(entity, FlightDto.class));
                        k++;
                    } else {
                        List<FlightEntity> flightEntities2 = flightRepository
                                .findAll(SpecificationUtils
                                        .byCityFrom_City(entity.getCityTo().getCity())
                                        .and(SpecificationUtils.byDateTimeDepGreaterThanEqual(entity.getDateTimeArr()))
                                        .and(SpecificationUtils.byDateTimeDepLessThanEqual(date))
                                        .and(SpecificationUtils.byCountOfPlacesGreaterThanEqual(searchForm.getCountOfPerson()))
                                        .and((root, criteriaQuery, criteriaBuilder) -> {
                                            root.fetch("cityTo");
                                            root.fetch("cityFrom");
                                            return null;
                                        }));
                        for (FlightEntity entity2 : flightEntities2) {
                            if (entity2.getCityTo().getCity().equals(searchForm.getCityTo())) {
                                flights.add(new ArrayList<>());
                                flightEntity.setDateTimeArr(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(flightEntity.getDateTimeArr().toString()));
                                flightEntity.setDateTimeDep(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(flightEntity.getDateTimeDep().toString()));
                                flights.get(k).add(modelMapper.map(flightEntity, FlightDto.class));
                                entity.setDateTimeArr(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(entity.getDateTimeArr().toString()));
                                entity.setDateTimeDep(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(entity.getDateTimeDep().toString()));
                                flights.get(k).add(modelMapper.map(entity, FlightDto.class));
                                entity2.setDateTimeArr(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(entity.getDateTimeArr().toString()));
                                entity2.setDateTimeDep(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(entity2.getDateTimeDep().toString()));
                                flights.get(k).add(modelMapper.map(entity2, FlightDto.class));
                                k++;
                            }
                        }
                    }

                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(flights);
        return flights;
    }

    public static class SpecificationUtils {

        public static Specification<FlightEntity> byPassengerId(Long id) {
            return ((root, criteriaQuery, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("passenger"), id);
            });
        }

        public static Specification<FlightEntity> byCityFrom_City(String city) {
            return new Specification<FlightEntity>() {
                @Override
                public Predicate toPredicate(Root<FlightEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Join<FlightEntity, CityEntity> cityEntityListJoin = root.join("cityFrom");
                    Predicate predicate = criteriaBuilder.equal(cityEntityListJoin.get("city"), city);
                    return predicate;
                }
            };
        }

        public static Specification<FlightEntity> byCityTo_City(String city) {
            return new Specification<FlightEntity>() {
                @Override
                public Predicate toPredicate(Root<FlightEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Join<FlightEntity, CityEntity> cityEntityListJoin = root.join("cityTo");
                    Predicate predicate = criteriaBuilder.equal(cityEntityListJoin.get("city"), city);
                    return predicate;
                }
            };
        }

        public static Specification<FlightEntity> byId(Long id) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("id"), id);
        }


        public static Specification<FlightEntity> byDateTimeDepGreaterThanEqual(Date date) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dateTimeDep"), date);
        }

        public static Specification<FlightEntity> byDateTimeDepLessThanEqual(Date date) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("dateTimeDep"), date);
        }

        public static Specification<FlightEntity> byCountOfPlacesGreaterThanEqual(int countOfPlaces) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("countOfPlaces"), countOfPlaces);
        }


    }

    public static double distance(double lat1, double lat2, double lon1, double lon2) {
        double EARTH_RADIUS = 6371.;
        double dlng = deg2rad(lon1 - lon2);
        double dlat = deg2rad(lat1 - lat2);
        double a = sin(dlat / 2) * sin(dlat / 2) + cos(deg2rad(lat2))
                * cos(deg2rad(lat1)) * sin(dlng / 2) * sin(dlng / 2);
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));
        System.out.println("distance: " + c * EARTH_RADIUS); // получаем расстояние в километрах
        return c * EARTH_RADIUS;
    }

    private static double deg2rad(final double degree) {
        return degree * (Math.PI / 180);
    }
}

