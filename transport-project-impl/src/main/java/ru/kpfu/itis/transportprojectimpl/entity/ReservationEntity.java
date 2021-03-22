package ru.kpfu.itis.transportprojectimpl.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "reservation")
public class ReservationEntity {
    @Id
    private Long id;

    @ManyToOne
    private FlightEntity flight;

    @ManyToOne
    public UserEntity passenger;

    @Enumerated(EnumType.STRING)
    private State state;

    public enum State {
        ACTIVE, COMPLETED
    }
}
