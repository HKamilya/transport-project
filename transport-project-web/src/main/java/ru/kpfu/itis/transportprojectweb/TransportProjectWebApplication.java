package ru.kpfu.itis.transportprojectweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.kpfu.itis.transportprojectapi.service.FlightService;
import ru.kpfu.itis.transportprojectimpl.service.FlightServiceImpl;

@SpringBootApplication
public class TransportProjectWebApplication {


    public static void main(String[] args) {
        SpringApplication.run(TransportProjectWebApplication.class, args);
    }

}
