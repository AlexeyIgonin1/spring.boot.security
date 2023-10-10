package com.spring.security.spring.boot.security.config;


import com.spring.security.spring.boot.security.security.PersonDetailsServiceImp;

import com.spring.security.spring.boot.security.security.SuccessUserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    @EnableWebSecurity
    public class ApplicationConfigurerAdapter {

    //private final PersonServiceImpl personService;
    //private final DataSource dataSource;

    private final PersonDetailsServiceImp personDetailsServiceImp;
    private final SuccessUserHandler successUserHandler; // класс, в котором описана логика перенаправления пользователей по ролям


        @Autowired
    public ApplicationConfigurerAdapter(@Qualifier("personDetailsServiceImp")PersonDetailsServiceImp personDetailsServiceImp,
                                        SuccessUserHandler successUserHandler) {



            this.personDetailsServiceImp = personDetailsServiceImp;
            this.successUserHandler = successUserHandler;
        }


    //настройка аутентификации
        @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(personDetailsServiceImp).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
    //шифрование пароля
        @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
}


        @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
            csrf((csrf) -> csrf.disable())

                .authorizeHttpRequests((requests) -> requests
                                //.requestMatchers("/main/index").hasRole("ADMIN")
                                .requestMatchers("/main/index").permitAll()
                                .requestMatchers("/login").permitAll()
                                .anyRequest().authenticated()

                )
                .formLogin((form) -> form
                        .successHandler(successUserHandler)//добавил
                        .loginPage("/auth/login").permitAll()
                        .usernameParameter("username")//добавил
                        .passwordParameter("password")//добавил
                        .defaultSuccessUrl("/main/index")
                )
                .logout((logout) -> logout.permitAll())
                .httpBasic();

        return http.build();


    }


}
