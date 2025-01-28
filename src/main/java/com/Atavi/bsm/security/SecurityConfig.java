package com.Atavi.bsm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // => If we don't use this annotation, then Spring Security is not enabled for the Application
@EnableMethodSecurity //=> This enables us to provide Authorization at Method Level in Controller Class itself instead of giving Permissions to
//Endpoints in SecurityFilterChain
public class SecurityConfig {

    @Bean //All Bean scope should be Package Scope that is 'default', we should not change it to 'public' or 'private'
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
                        authorize.requestMatchers("/registerUser","/blood-Banks","/samples","/adminUsers")// Deciding which endpoints to be given permission to use without Authentication
                                .permitAll()// THis line permits the endpoints mentioned above
                            //    .requestMatchers("/users/{userId}") // Authorizing particular endPoint means giving authorities to do any task
                            //    .hasAuthority("OWNER_ADMIN") // Role Based Authorization
// The Above two lines has been added At Method Level in Controller Class for Authorization to avoid lengthier method VarArgs arguments
                                .anyRequest()// Other than above endpoints
                                .authenticated()) //Should be authenticated to access the endpoints
                .formLogin(Customizer.withDefaults()) // if any customized authentication is provided then it should be given here, or else we can go with default
                .build();

    }

      /* When we want to access third-party libraries such as Jar files or any dependency we cant use any
    Stereotype annotations like @Component , @Service etc on the provider class or Interface
    So, if we want any particular type of Third-party provider, then we can use @Bean on the method which will have the return type of Required Third Party
    Library's Interface or CLass, so that we could have the required Class type to use instead of creating a new class & write code and do annotate with
    @Bean or any other Stereo type annotations which will be useless when we have Ready made Classes or Interface in third party libraires
     */

    /*
    While adding Security Chain Filter
    1>  Since we dont have required privileges in our application , Enforcement of CSRF protection Tag in Typically dealing with Webservices will be
    disabled first
    2> private and public endpoints : Providing Basic info to spring about the Public and Private Endpoints
    3> Type of Authentication used in Application: Http Session ( Session Based), Stateless,Auth2.0

Authentication provide -> UserDetails ->

Bycript is one way algorithm we can only encrypt cant decrypt and used in our application as one of the Password Encoder
     */
}
