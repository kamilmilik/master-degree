package com.example.masterdegree;

import com.example.masterdegree.controllers.OperatorsController;
import com.example.masterdegree.trash.UsersController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackageClasses = {UsersController.class, OperatorsController.class})
@ComponentScan(basePackages = {"com.example.masterdegree"})

public class MasterDegreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterDegreeApplication.class, args);
    }

}
