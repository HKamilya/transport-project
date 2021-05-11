package ru.kpfu.itis.transportprojectimpl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectimpl.entity.CityEntity;
import ru.kpfu.itis.transportprojectimpl.entity.FlightEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaSpecificationExecutor<FlightEntity>, JpaRepository<FlightEntity, Long> {


    public List<FlightEntity> findAllByCityFrom_CityAndCityTo_CityAndDateTimeDepGreaterThanEqualAndCountOfPlacesGreaterThanEqual(String cityFrom, String cityTo, Date date, int numOfPass);


    public Page<FlightEntity> findAll(Pageable pageable);



    public List<FlightEntity> findAllByCountOfPlacesGreaterThanEqual(int num);

    public List<FlightEntity> findAllByCityFrom_CityAndDateTimeDepGreaterThanEqualAndDateTimeDepLessThanEqualAndCountOfPlacesGreaterThanEqual(String city, Date date, Date date2, int places);


    public void deleteById(Long id);

    public Optional<FlightEntity> findById(Long id);

}
