package com.alexkariotis.gymapp;

import com.alexkariotis.gymapp.domain.entity.EntityConfiguration;
import com.alexkariotis.gymapp.domain.repository.RepositoryConfiguration;
import com.alexkariotis.gymapp.service.ServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        ServiceConfiguration.class,
        EntityConfiguration.class,
        RepositoryConfiguration.class
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
