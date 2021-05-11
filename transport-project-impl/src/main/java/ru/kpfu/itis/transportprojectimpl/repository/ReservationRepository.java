package ru.kpfu.itis.transportprojectimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.kpfu.itis.transportprojectimpl.entity.ReservationEntity;

import java.util.List;

public interface ReservationRepository extends JpaSpecificationExecutor<ReservationEntity>,  JpaRepository<ReservationEntity, Long> {

    public List<ReservationEntity> findAllByPassenger_Id(Long id);
}
