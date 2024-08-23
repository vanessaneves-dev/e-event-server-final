package com.projetofinal.eeventserverfinal.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
   private SecurityFilter securityFilter; ;

   @Autowired
   private SecurityUserFilter securityUserFilter; ;




    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("api/user/new").permitAll()
                            .requestMatchers("api/organizer/new").permitAll()
                            .requestMatchers("api/organizer/auth").permitAll()
                            .requestMatchers("api/user/auth").permitAll()
                            .requestMatchers("api/user/authok").permitAll()
                            .requestMatchers("api/organizer").permitAll()
                    ;
                    auth.anyRequest().authenticated();

                })
                .addFilterBefore( securityUserFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(securityFilter, BasicAuthenticationFilter.class)

        ;
        return http.build();
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
