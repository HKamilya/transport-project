package ru.kpfu.itis.transportprojectimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kpfu.itis.transportprojectapi.service.FlightService;

@SpringBootApplication
public class TransportProjectImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransportProjectImplApplication.class, args);
    }

}
