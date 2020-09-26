package com.proha.TriveniStores.config;

import com.proha.TriveniStores.domain.Roles;
import com.proha.TriveniStores.service.UserManagementService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@ComponentScan("com.proha.TriveniStores.domain")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // Authentication : User --> Roles
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(getUserManagementService()).passwordEncoder(bCryptPasswordEncoder()).and()
                .inMemoryAuthentication().passwordEncoder(bCryptPasswordEncoder())
                .withUser("user1").password("password1").authorities(Roles.USER.type)
                .and()
                .withUser("proha").password("proha@123")
                .authorities(Roles.ADMIN.type, Roles.USER.type);
    }

    // Authorization : Role -> Access
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers("/").hasAuthority(Roles.USER.type)
                .antMatchers("/loggedIn").hasAnyAuthority(Roles.ADMIN.type, Roles.USER.type)
                .antMatchers("/admin").hasAuthority(Roles.ADMIN.type)
                .anyRequest().authenticated()
                .and()
                .csrf().disable().headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService getUserManagementService(){
        return new UserManagementService();
    }
}
