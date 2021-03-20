package ru.kpfu.itis.transportprojectweb.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.kpfu.itis.transportprojectimpl.config.ImplConfiguration;

@EnableWebMvc
@Configuration
@Import({ImplConfiguration.class})
public class WebConfiguration {


}
