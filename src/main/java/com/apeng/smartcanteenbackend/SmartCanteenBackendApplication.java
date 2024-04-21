package com.apeng.smartcanteenbackend;

import com.apeng.smartcanteenbackend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmartCanteenBackendApplication {

    private static final Logger log = LoggerFactory.getLogger(SmartCanteenBackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SmartCanteenBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserService userService) {
        return (args) -> {
            userService.addUser("admin", "123456", "ROLE_USER");
        };
    }

}
