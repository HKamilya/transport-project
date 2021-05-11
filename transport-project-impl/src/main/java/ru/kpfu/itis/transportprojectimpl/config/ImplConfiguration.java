package ru.kpfu.itis.transportprojectimpl.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kpfu.itis.transportprojectimpl.util.JsonReader;

@Configuration
//@ComponentScan("ru.kpfu.itis.demo.blog.impl.repository")
@ComponentScan({"ru.kpfu.itis.transportprojectimpl.service",
        "ru.kpfu.itis.transportprojectimpl.aspect",
        "ru.kpfu.itis.transportprojectimpl.util"})
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = "ru.kpfu.itis.transportprojectimpl.repository")
@EntityScan(basePackages = "ru.kpfu.itis.transportprojectimpl.entity")
@EnableAspectJAutoProxy
public class ImplConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
