package ru.kpfu.itis.transportprojectimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.transportprojectimpl.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
