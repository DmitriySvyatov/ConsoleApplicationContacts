package com.example.spring.config;


import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@ComponentScan("com.example.spring")
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("application.properties"));
        return configurer;
    }

}
