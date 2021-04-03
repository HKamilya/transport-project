package ru.kpfu.itis.transportprojectimpl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectimpl.entity.FlightEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<FlightEntity, Long> {


    public List<FlightEntity> findAllByCityFromAndCityToAndDateDepAndCountOfPlacesGreaterThanEqual(String cityFrom, String cityTo, Date date, int numOfPass);


    public Page<FlightEntity> findAll(Pageable pageable);

    public void deleteById(Long id);

    public Optional<FlightEntity> findById(Long id);
}
