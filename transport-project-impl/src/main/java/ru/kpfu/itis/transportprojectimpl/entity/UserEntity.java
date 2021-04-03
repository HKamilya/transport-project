package ru.kpfu.itis.transportprojectimpl.entity;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@ToString
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
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Enumerated(value = EnumType.STRING)
    private AuthProvider auth_provider;

    public enum Role {
        ADMIN, USER
    }

    public enum AuthProvider {
        LOCAL, GOOGLE
    }

    public Boolean isAdmin() {
        return this.role == Role.ADMIN;
    }
}
