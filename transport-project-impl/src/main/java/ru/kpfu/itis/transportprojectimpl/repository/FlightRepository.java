package ru.kpfu.itis.transportprojectimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.transportprojectimpl.entity.FlightEntity;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<FlightEntity, Long> {


    public List<FlightEntity> findAllByCityFromAndCityToAndDateDepAndCountOfPlacesGreaterThanEqual(String cityFrom, String cityTo, Date date, int numOfPass);

}
