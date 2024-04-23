package com.apeng.smartcanteenbackend.config;

import com.apeng.smartcanteenbackend.entity.Users;
import com.apeng.smartcanteenbackend.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CommonConfig {

    public static String ADMIN_USERNAME = "admin";
    public static String ADMIN_PASSWORD = "123456";

    @Bean
    public RestTemplate configureRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    RestOperations configureRestOperations(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.basicAuthentication(ADMIN_USERNAME, ADMIN_PASSWORD).build();
    }

    @Bean
    public CommandLineRunner addAdminUser(UsersRepository repository) {
        return (args) -> repository.save(new Users(CommonConfig.ADMIN_USERNAME, CommonConfig.ADMIN_PASSWORD));
    }

}
