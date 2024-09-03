package com.example.bankmanagement.Service;

import com.example.bankmanagement.Api.ApiException;
import com.example.bankmanagement.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(authRepository.findUserByUsername(username) == null)
        {
            throw new ApiException("Wrong Username or Password");
        }

        return authRepository.findUserByUsername(username);
    }
}
