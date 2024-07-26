package com.udemy.springsecurity.config;

import com.udemy.springsecurity.exceptionhandling.CustomAccessDeniedHandler;
import com.udemy.springsecurity.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;

    @Bean
    SecurityFilterChain customSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(requests -> requests
                .requestMatchers("/contact", "/register").permitAll()
                .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable);
        httpSecurity.formLogin(withDefaults());
        httpSecurity.httpBasic(h -> h.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        httpSecurity.exceptionHandling(e -> e.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return httpSecurity.build();
    }

//    @Bean
//    public UserDetailsService customUserDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder customPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
