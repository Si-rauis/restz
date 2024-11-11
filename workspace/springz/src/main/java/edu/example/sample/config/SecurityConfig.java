package edu.example.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(req ->       //요청 관련 인증 설정
                req.requestMatchers(HttpMethod.POST, "/display-sample/**")
                   .hasRole("ADMIN")
                   .requestMatchers("/display-sample/**").hasAnyRole("ADMIN", "STAFF")
                   .anyRequest().permitAll())
            .formLogin(login ->                 //로그인 화면 설정
                login.loginPage("/login")
                     .defaultSuccessUrl("/display-sample")
                     .failureUrl("/login?failure"))
            .exceptionHandling(handling ->      //예외 - 접근 거부 설정
                handling.accessDeniedPage("/display-access-denied"));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails hana = User.builder().username("hana")
                                         .password("{noop}1111")
                                         .roles("ADMIN")
                                         .build();

        UserDetails dool = User.builder().username("dool")
                                         .password("{noop}1111")
                                         .roles("STAFF")
                                         .build();

        UserDetails saam = User.builder().username("saam")
                                         .password("{noop}1111")
                                         .roles("GUEST")
                                         .build();

        return new InMemoryUserDetailsManager(hana, dool, saam);
    }
}













