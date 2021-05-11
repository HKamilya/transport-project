package ru.kpfu.itis.transportprojectimpl.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.kpfu.itis.transportprojectimpl.entity.ReservationEntity;
import ru.kpfu.itis.transportprojectimpl.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaSpecificationExecutor<UserEntity>, JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByEmailOrUsername(String email, String username);

    @Override
    Optional<UserEntity> findOne(Specification<UserEntity> specification);
}
