package com.example.masterdegree;

import com.example.masterdegree.controllers.OperatorsController;
import com.example.masterdegree.trash.UsersController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {UsersController.class, OperatorsController.class})
@ComponentScan(basePackages = {"com.example.masterdegree"})

public class MasterDegreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterDegreeApplication.class, args);
    }

}
