package com.Atavi.bsm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean //All Bean scope should be Package Scope that is 'default'
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //Reference name of the bean would be the class name itself ,
    @Bean
    //If a Bean accepting any Parameter, then that parameter should be also of Bean type so that no need of creating Bean again
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception
    {
        return http.csrf(csrf->csrf.disable()) // Disabling CSRF Protection provided by SpringSecurity by default
                .authorizeHttpRequests(authorize->
                        authorize.requestMatchers("/registerUser","/blood-Banks")// Deciding which endpoints to be given permission to use without Authentication
                                .permitAll()// THis line permits the endpoints mentioned above
                                .anyRequest()// Other than above endpoints
                                .authenticated())
                .formLogin(Customizer.withDefaults())
                .build();

    }

      /* When we want to access third-party libraries such as Jar files or any dependency we cant use any
    Stereotype annotations like @Component , @Service etc on the provider class or Interface
    So, if want any particular type of Third-party provider

    1>  Enforcement of CSRF protection Tag in Typically dealing with Webservices
    2> private and public endpoints
    3> Type of Authentication Http Session ( Session Based), Stateless,Auth2.0

Authentication provide -> UserDetails ->

Bycript is one way algorithm we can only encrypt cant decrypt


     */
}
