package ru.kpfu.itis.transportprojectimpl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.transportprojectimpl.validation.PasswordMatch;
import ru.kpfu.itis.transportprojectimpl.validation.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatch(
        message = "password doesn't match",
        password = "password",
        confirmPassword = "confirmPassword"
)
public class UserForm {
    private Long id;
    @Email(message = "email is not correct")
    @UniqueEmail
    @NotBlank(message = "please enter email")
    private String email;
    private String firstname;
    private String lastname;
    @NotBlank(message = "password cannot be empty")
    private String password;
    @NotBlank(message = "password confirmation cannot be empty")
    private String confirmPassword;
    @NotBlank(message = "username cannot be empty")
    private String username;
    private String phoneNumber;
    private String dateOfBirth;
}
