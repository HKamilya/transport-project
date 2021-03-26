package ru.kpfu.itis.transportprojectweb.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.kpfu.itis.transportprojectimpl.config.ImplConfiguration;

@Configuration
@Import({ImplConfiguration.class})
public class WebConfiguration {


}
