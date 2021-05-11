package ru.kpfu.itis.transportprojectimpl.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {

    private String passwordPropertyName;
    private String confirmPasswordPropertyName;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        this.passwordPropertyName = constraintAnnotation.password();
        this.confirmPasswordPropertyName = constraintAnnotation.confirmPassword();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object password = new BeanWrapperImpl(value).getPropertyValue(passwordPropertyName); //получили конкретные значения
        Object password2 = new BeanWrapperImpl(value).getPropertyValue(confirmPasswordPropertyName);

        return password != null && password.equals(password2);
    }
}
