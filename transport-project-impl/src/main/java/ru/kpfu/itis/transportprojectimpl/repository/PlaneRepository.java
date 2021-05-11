package ru.kpfu.itis.transportprojectimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.kpfu.itis.transportprojectimpl.entity.PlaneEntity;

public interface PlaneRepository extends JpaSpecificationExecutor<PlaneEntity>, JpaRepository<PlaneEntity, Long> {
    public PlaneEntity findByName(String name);
}
