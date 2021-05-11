package ru.kpfu.itis.transportprojectimpl.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@ToString
@Table(name = "account")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String email;
    private String firstname;
    private String password;
    private String lastname;
    @Column(unique = true)
    private String username;
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Enumerated(value = EnumType.STRING)
    private AuthProvider auth_provider;
    private String refreshToken;

    public enum Role {
        ADMIN, USER
    }

    public enum AuthProvider {
        LOCAL, GOOGLE
    }

    @OneToMany(mappedBy = "passenger", fetch = FetchType.EAGER)
    @ToString.Exclude
    public List<ReservationEntity> reservations;

    public Boolean isAdmin() {
        return this.role == Role.ADMIN;
    }
}
