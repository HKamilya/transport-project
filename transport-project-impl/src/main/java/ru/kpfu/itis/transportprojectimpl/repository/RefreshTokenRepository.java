package ru.kpfu.itis.transportprojectimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.transportprojectimpl.entity.RefreshTokenEntity;
import ru.kpfu.itis.transportprojectimpl.entity.UserEntity;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    @Override
    Optional<RefreshTokenEntity> findById(Long id);

    Optional<RefreshTokenEntity> findByToken(String token);

    void deleteByUser(UserEntity userEntity);
}
