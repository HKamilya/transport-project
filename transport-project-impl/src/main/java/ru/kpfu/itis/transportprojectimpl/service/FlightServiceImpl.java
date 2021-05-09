package ru.kpfu.itis.transportprojectimpl.service;

import com.google.maps.errors.ApiException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportprojectapi.dto.*;
import ru.kpfu.itis.transportprojectapi.service.CityService;
import ru.kpfu.itis.transportprojectapi.service.FlightService;
import ru.kpfu.itis.transportprojectapi.service.PlaneService;
import ru.kpfu.itis.transportprojectimpl.aspect.Cacheable;
import ru.kpfu.itis.transportprojectimpl.entity.CityEntity;
import ru.kpfu.itis.transportprojectimpl.entity.FlightEntity;
import ru.kpfu.itis.transportprojectimpl.entity.PlaneEntity;
import ru.kpfu.itis.transportprojectimpl.repository.FlightRepository;
import ru.kpfu.itis.transportprojectimpl.util.JsonReader;

import javax.persistence.criteria.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Autowired
    private PlaneService planeService;


    @Override
    public FlightDto save(FlightDto flightDto) {
        FlightEntity entity = flightRepository.save(modelMapper.map(flightDto, FlightEntity.class));
        return modelMapper.map(entity, FlightDto.class);
    }

    @Override
    public FlightDto save(FlightForm form) {
        PlaneDto planeDto = planeService.findById(form.getPlaneType());
        Date dateTimeDep = null;
        Date dateArr = null;
        JsonReader jsonReader = new JsonReader();
        try {
            double[] to = jsonReader.main(form.getCityTo(), form.getCountryTo());
            double[] from = jsonReader.main(form.getCityFrom(), form.getCountryFrom());
            double distance = distance(to[0], from[0], to[1], from[1]);
            double duration = Math.abs(distance) / planeDto.getSpeed() * 60 * 60 * 1000 + 3000000;

            dateTimeDep = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(form.getDateTimeDep().replace('T', ' ') + ":00");
            long time1 = dateTimeDep.getTime();
            long time2;
            time2 = (long) (time1 + duration);
            dateArr = dateTimeDep;
            dateArr.setTime(time2);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            dateArr = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(df.format(dateArr));
            dateTimeDep = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(form.getDateTimeDep().replace('T', ' '));


            FlightEntity flightEntity = new FlightEntity();
            modelMapper.map(form, flightEntity);
            flightEntity.setCityTo(modelMapper.map(cityService.findByName(form.getCityTo()).get(), CityEntity.class));
            flightEntity.setCityFrom(modelMapper.map(cityService.findByName(form.getCityFrom()).get(), CityEntity.class));
            flightEntity.setDateTimeArr(dateArr);
            flightEntity.setDateTimeDep(dateTimeDep);
            flightEntity.setPlaneType(modelMapper.map(planeDto, PlaneEntity.class));
            flightEntity.setDistance(distance);
            flightEntity.setCountOfPlaces(planeDto.getCountOfPlaces());
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
        return flightRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            if (criteriaQuery.getResultType().equals(Long.class)) {
                return null;
            } else {
                root.fetch("cityTo");
                root.fetch("cityFrom");
                root.fetch("planeType");
                return null;
            }
        }, pageable).map(flightEntity -> modelMapper.map(flightEntity, FlightDto.class));
        //    return flightRepository.findAll(pageable).map(flightEntity -> modelMapper.map(flightEntity, FlightDto.class));
    }

    public List<FlightEntity> search(SearchForm searchForm) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(searchForm.getDate() + " 00:00:00");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(searchForm.getDate()));
            c.add(Calendar.DATE, 2);
            String dateStr = sdf.format(c.getTime());
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);

            List<FlightEntity> flightEntities = flightRepository
                    .findAll(SpecificationUtils
                            .byCityFrom_City(searchForm.getCityFrom())
                            .and(SpecificationUtils.byCityTo_City(searchForm.getCityTo()))
                            .and(SpecificationUtils.byDateTimeDepGreaterThanEqual(date))
                            .and(SpecificationUtils.byDateTimeDepLessThanEqual(date1))
                            .and(SpecificationUtils.byCountOfPlacesGreaterThanEqual(searchForm.getCountOfPerson()))
                            .and((root, criteriaQuery, criteriaBuilder) -> {
                                root.fetch("cityTo");
                                root.fetch("cityFrom");
                                root.fetch("planeType");
                                return null;
                            }));
            List<FlightEntity> flightDtoList = new ArrayList<>();
            for (FlightEntity flight : flightEntities) {
                flightDtoList.add(flight);
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
    @Cacheable
    public Optional<FlightDto> findById(Long id) {
        Optional<FlightEntity> flightOptional = flightRepository
                .findOne(SpecificationUtils.byId(id)
                        .and((root, criteriaQuery, criteriaBuilder) -> {
                            root.fetch("cityTo")
                                    .getParent().fetch("cityFrom")
                                    .getParent().fetch("planeType");
                            return null;
                        }));
        return flightOptional.map(flightEntity -> Optional.of(modelMapper.map(flightEntity, FlightDto.class))).orElse(null);
    }


    @Override
    public List<FlightsList> findOptimalWayByDistance(SearchForm searchForm) {
        List<FlightsList> flights = new ArrayList<>();
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(searchForm.getDate());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(searchForm.getDate()));
            c.add(Calendar.DATE, 2);
            String dateStr = sdf.format(c.getTime());
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            int k = 0;
            List<FlightEntity> flightDtoList = search(searchForm);
            for (FlightEntity flightDto : flightDtoList) {
                FlightsList flightsList = new FlightsList();
                flightsList.setFlights(new ArrayList<>());
                FlightDto flightDto1 = modelMapper.map(flightDto, FlightDto.class);
                flightsList.getFlights().add(flightDto1);
                flightsList.setDistance(flightDto.getDistance());
                flightsList.setPrice(flightDto.getPrice());
                flightsList.setTime(flightDto.getDateTimeArr().getTime() - flightDto.getDateTimeDep().getTime());
                flights.add(flightsList);
                k++;
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
                                root.fetch("planeType");
                                return null;
                            }));
            for (FlightEntity flightEntity : flightEntities) {
                Date date2 = flightEntity.getDateTimeArr();
                Long time = date2.getTime() + 3600000;
                date2.setTime(time);

                List<FlightEntity> flightEntities1 = flightRepository
                        .findAll(SpecificationUtils
                                .byCityFrom_City(flightEntity.getCityTo().getCity())
                                .and(SpecificationUtils.byDateTimeDepGreaterThanEqual(date2))
                                .and(SpecificationUtils.bySameAirport(flightEntity.getAirportTo()))
                                .and(SpecificationUtils.byDateTimeDepLessThanEqual(date1))
                                .and(SpecificationUtils.byCountOfPlacesGreaterThanEqual(searchForm.getCountOfPerson()))
                                .and((root, criteriaQuery, criteriaBuilder) -> {
                                    root.fetch("cityTo");
                                    root.fetch("cityFrom");
                                    return null;
                                }));
                for (FlightEntity entity : flightEntities1) {

                    if (entity.getCityTo().getCity().equals(searchForm.getCityTo())) {
                        FlightsList flightsList = new FlightsList();
                        flightsList.setFlights(new ArrayList<>());
                        flights.add(flightsList);
                        flights.get(k).getFlights().add(modelMapper.map(flightEntity, FlightDto.class));
                        flights.get(k).setDistance(flights.get(k).getDistance() + flightEntity.getDistance());
                        flights.get(k).setPrice(flights.get(k).getPrice() + flightEntity.getPrice());
                        flights.get(k).getFlights().add(modelMapper.map(entity, FlightDto.class));
                        flights.get(k).setDistance(flights.get(k).getDistance() + entity.getDistance());
                        flights.get(k).setPrice(flights.get(k).getPrice() + entity.getPrice());
                        flights.get(k).setTime(entity.getDateTimeArr().getTime() - flightEntity.getDateTimeDep().getTime());
                        k++;
                    } else {
                        date2 = entity.getDateTimeArr();
                        time = date2.getTime() + 3600000;
                        date2.setTime(time);
                        List<FlightEntity> flightEntities2 = flightRepository
                                .findAll(SpecificationUtils
                                        .byCityFrom_City(entity.getCityTo().getCity())
                                        .and(SpecificationUtils.byDateTimeDepGreaterThanEqual(date2))
                                        .and(SpecificationUtils.bySameAirport(entity.getAirportTo()))
                                        .and(SpecificationUtils.byDateTimeDepLessThanEqual(date1))
                                        .and(SpecificationUtils.byCountOfPlacesGreaterThanEqual(searchForm.getCountOfPerson()))
                                        .and((root, criteriaQuery, criteriaBuilder) -> {
                                            root.fetch("cityTo");
                                            root.fetch("cityFrom");
                                            return null;
                                        }));
                        for (FlightEntity entity2 : flightEntities2) {
                            if (entity2.getCityTo().getCity().equals(searchForm.getCityTo())) {
                                FlightsList flightsList = new FlightsList();
                                flightsList.setFlights(new ArrayList<>());
                                flights.add(flightsList);
                                flights.get(k).getFlights().add(modelMapper.map(flightEntity, FlightDto.class));
                                flights.get(k).setDistance(flights.get(k).getDistance() + flightEntity.getDistance());
                                flights.get(k).setPrice(flights.get(k).getPrice() + flightEntity.getPrice());
                                flights.get(k).getFlights().add(modelMapper.map(entity, FlightDto.class));
                                flights.get(k).setDistance(flights.get(k).getDistance() + entity.getDistance());
                                flights.get(k).setPrice(flights.get(k).getPrice() + entity.getPrice());

                                flights.get(k).getFlights().add(modelMapper.map(entity2, FlightDto.class));
                                flights.get(k).setDistance(flights.get(k).getDistance() + entity2.getDistance());
                                flights.get(k).setPrice(flights.get(k).getPrice() + entity2.getPrice());
                                flights.get(k).setTime(entity2.getDateTimeArr().getTime() - flightEntity.getDateTimeDep().getTime());

                                k++;
                            }
                        }
                    }

                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return flights;
    }

    @Override
    public Page<FlightDto> findByCity(String city, Pageable pageable) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Date now = new Date();
            c.setTime(now);
            c.add(Calendar.DATE, 2);
            String dateStr = sdf.format(c.getTime());
            Date date1 = null;
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);


            return flightRepository.findAll(SpecificationUtils.byCityFrom_City(city)
                    .and(SpecificationUtils.byDateTimeDepGreaterThanEqual(now))
                    .and(SpecificationUtils.byDateTimeDepLessThanEqual(date1))
                    .and(((root, criteriaQuery, criteriaBuilder) -> {
                        if (criteriaQuery.getResultType().equals(Long.class)) {
                            return null;
                        } else {
                            root.fetch("cityTo");
                            root.fetch("cityFrom");
                            root.fetch("planeType");
                            return null;
                        }
                    })), pageable).map(flightEntity -> modelMapper.map(flightEntity, FlightDto.class));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class SpecificationUtils {

        public static Specification<FlightEntity> byId(Long id) {
            return (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("id"), id);
        }


        public static Specification<FlightEntity> byCityFrom_City(String city) {
            return new Specification<FlightEntity>() {
                @Override
                public Predicate toPredicate(Root<FlightEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Join<FlightEntity, CityEntity> cityEntityListJoin = root.join("cityFrom", JoinType.LEFT);
                    Predicate predicate = criteriaBuilder.equal(cityEntityListJoin.get("city"), city);
                    return predicate;
                }
            };
        }

        public static Specification<FlightEntity> byCityTo_City(String city) {
            return new Specification<FlightEntity>() {
                @Override
                public Predicate toPredicate(Root<FlightEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Join<FlightEntity, CityEntity> cityEntityListJoin = root.join("cityTo", JoinType.LEFT);
                    Predicate predicate = criteriaBuilder.equal(cityEntityListJoin.get("city"), city);
                    return predicate;
                }
            };
        }


        public static Specification<FlightEntity> byDateTimeDepGreaterThanEqual(Date date) {
            return (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("dateTimeDep"), date);
        }

        public static Specification<FlightEntity> byDateTimeDepLessThanEqual(Date date) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("dateTimeDep"), date);
        }

        public static Specification<FlightEntity> bySameAirport(String airport) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("airportFrom"), airport);
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

