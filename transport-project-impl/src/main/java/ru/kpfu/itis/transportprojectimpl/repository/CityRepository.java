package ru.kpfu.itis.transportprojectimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.kpfu.itis.transportprojectimpl.entity.CityEntity;

import java.util.Optional;

public interface CityRepository extends JpaSpecificationExecutor<CityEntity>, JpaRepository<CityEntity, Long> {

    Optional<CityEntity> findByCity(String city);
}
