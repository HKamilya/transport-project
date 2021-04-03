package ru.kpfu.itis.transportprojectweb.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import ru.kpfu.itis.transportprojectimpl.config.ImplConfiguration;

@Configuration
@Import({ImplConfiguration.class})
public class WebConfiguration {



}
