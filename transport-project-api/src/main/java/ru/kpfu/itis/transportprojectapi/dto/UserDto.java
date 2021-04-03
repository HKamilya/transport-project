package ru.kpfu.itis.transportprojectapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String firstname;
    private String password;
    private String lastname;
    private String username;
    private String phoneNumber;
    private String dateOfBirth;

}
