package com.atabey.todo_management.config;

import com.atabey.todo_management.security.JwtAuthenticationEntryPoint;
import com.atabey.todo_management.security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
@ComponentScan(basePackages = "com.atabey.todo_management.security")
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService;

    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize)-> {
/*
                authorize.requestMatchers(HttpMethod.POST, "/api/todo/**").hasRole("ADMIN");
                authorize.requestMatchers(HttpMethod.PUT, "/api/todo/**").hasRole("ADMIN");
                authorize.requestMatchers(HttpMethod.DELETE, "/api/todo/**").hasRole("ADMIN");
                authorize.requestMatchers(HttpMethod.GET, "/api/todo/**").hasAnyRole("ADMIN", "USER");
                authorize.requestMatchers(HttpMethod.PATCH, "/api/todo/**").hasAnyRole("ADMIN", "USER");
                authorize.requestMatchers(HttpMethod.GET, "api/todo/**").permitAll();
  */
                    authorize.requestMatchers("api/auth/**").permitAll();
                   authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
        http.exceptionHandling(exception->
                exception.authenticationEntryPoint(authenticationEntryPoint));
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
/*
    public UserDetailsService userDetailsService(){

        UserDetails gazi= User.builder()
                .username("gazi")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(gazi, admin);
    }
*/

}
