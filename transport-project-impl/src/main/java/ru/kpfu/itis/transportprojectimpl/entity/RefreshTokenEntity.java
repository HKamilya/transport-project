package ru.kpfu.itis.transportprojectimpl.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Table
@Data
@Entity(name = "refreshToken")
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;


}
