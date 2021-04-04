package ru.kpfu.itis.transportprojectweb.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.transportprojectimpl.UserForm;
import ru.kpfu.itis.transportprojectapi.dto.UserDto;
import ru.kpfu.itis.transportprojectapi.service.UserService;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class SignUpController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @PermitAll
    @GetMapping("/signUp")
    public String signUpPage() {
        return "signUpPage";
    }

    @PermitAll
    @PostMapping("/signUp")
    public String signUp(@Valid UserForm userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                    fieldError -> fieldError.getField() + "Error",
                    FieldError::getDefaultMessage
            );
            bindingResult.getAllErrors().stream().anyMatch(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("userForm.ValidNames")) {
                    model.addAttribute("passwordsErrorMessage", error.getDefaultMessage());
                    System.out.println("pass");
                }
                return true;
            });
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream().collect(collector);
            model.mergeAttributes(errorsMap);
            model.addAttribute("userForm", userForm);
            return "signUpPage";
        } else {
            UserDto userDto = userService.findByEmail(userForm.getEmail());
            if (userDto == null) {
                userService.signUp(modelMapper.map(userForm, UserDto.class));
                return "redirect:/profile";
            } else {
                model.addAttribute("existErr", "It seems that such user already exists.");
            }
            return "signUpPage";
        }
    }
}
