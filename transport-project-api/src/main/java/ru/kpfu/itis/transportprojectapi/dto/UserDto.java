package ru.kpfu.itis.transportprojectapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String email;
    private String firstname;
    private String password;
    private String lastname;
    private String username;
    private String phoneNumber;
    private String dateOfBirth;

}
