package com.example.bankmanagement.ConfigSecurity;

import com.example.bankmanagement.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v7/auth/get-all","/api/v7/auth/register","/api/v7/auth/update/{id}","/api/v7/auth/delete/{id}","/api/v7/customer/get-all-customer","/api/v7/employee/get-all","/api/v7/account/get-all-account","/api/v7/account/block-account/{accountID}").hasAuthority("ADMIN")
                .requestMatchers("api/v7/customer/get-my-customer","/api/v7/customer/add-customer","/api/v7/customer/update-customer/{customerID}","api/v7/customer/delete-customer/{customerID}","api/v7/account/delete-account/{accountID}","/api/v7/account/add-account","/api/v7/account/turn-active/{id}","/api/v7/account/get-my-account","/api/v7/account/transfer-funds/{fromAccountID}/{toAccountID}/{amount}","/api/v7/account/update-account/{accountID}","/api/v7/account/withdraw-and-deposit-money/{accountID}/{amount}/{c}","/api/v7/account/view-my-account-details/{accountID}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v7/employee/get-my","/api/v7/employee/update-employee/{employeeID}","/api/v7/employee/add-employee","/api/v7/employee/delete-employee/{employeeID}").hasAnyAuthority("EMPLOYEE")
//                .requestMatchers("").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();

        return http.build();
    }
}
