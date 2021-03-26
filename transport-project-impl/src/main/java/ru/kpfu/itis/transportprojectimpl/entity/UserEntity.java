package ru.kpfu.itis.transportprojectimpl.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "account")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String firstname;
    private String password;
    private String lastname;
    private String username;
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private State state;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    public enum State {
        CONFIRMED, NOT_CONFIRMED, BANNED
    }

    public enum Role {
        ADMIN, USER
    }

    public Boolean isBanned() {
        return this.state == State.BANNED;
    }

    public Boolean isAdmin() {
        return this.role == Role.ADMIN;
    }
}
