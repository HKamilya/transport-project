package ru.kpfu.itis.transportprojectimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.transportprojectimpl.entity.FlightEntity;

public interface FlightRepository extends JpaRepository<FlightEntity, Long> {


}
