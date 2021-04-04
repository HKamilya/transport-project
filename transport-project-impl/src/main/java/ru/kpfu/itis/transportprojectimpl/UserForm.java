package ru.kpfu.itis.transportprojectimpl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.transportprojectimpl.validation.ValidNames;
import ru.kpfu.itis.transportprojectimpl.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidNames(
        message = "password doesn't match",
        password = "password",
       password2 = "password2"
)
public class UserForm {
    private Long id;
    @Email(message = "email is not correct")
    @NotBlank(message = "please enter email")
    private String email;
    private String firstname;
    private String lastname;
    @NotBlank(message = "password cannot be empty")
    @ValidPassword(message = "invalid password")
    private String password;
    @NotBlank(message = "password confirmation cannot be empty")
    private String password2;
    @NotBlank(message = "username cannot be empty")
    private String username;
    private String phoneNumber;
    private String dateOfBirth;
}
