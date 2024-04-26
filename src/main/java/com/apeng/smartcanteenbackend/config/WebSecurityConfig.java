package com.apeng.smartcanteenbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {

    /**
     * 允许跨域请求
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }

    /**
     * 配置密码编码方式。认证密码时，先用此编码器将密码编码后，再与数据库中的密码进行比对。
     *
     * @return
     */
    @Bean
    PasswordEncoder configurePasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置用户数据的获取方式为通过 JDBC 连接获取数据库用户表中的用户数据。
     *
     * @param jdbcTemplate
     * @return
     */
    @Bean
    UserDetailsService configureUserDetailsService(JdbcTemplate jdbcTemplate) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setJdbcTemplate(jdbcTemplate);
        return jdbcUserDetailsManager;
    }

    /**
     * 配置安全过滤器。仅允许认证的用户访问特定 API。
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain configureSecurityChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequests -> {
                    authorizeHttpRequests.requestMatchers("/**").hasRole("USER");
                }).csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

}
