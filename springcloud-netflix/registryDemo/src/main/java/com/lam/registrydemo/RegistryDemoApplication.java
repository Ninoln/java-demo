package com.lam.registrydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistryDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistryDemoApplication.class, args);
    }

}
