package com.example.bankmanagement.Service;

import com.example.bankmanagement.Api.ApiException;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public List<User> getAllAuths()
    {
        return authRepository.findAll();
    }

    public void register(User user)
    {
        user.setRole("CUSTOMER");
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        authRepository.save(user);
    }

    public void updateAuth(Integer id,User user)
    {
        User u = authRepository.findUserById(id);
        if(u == null)
        {
            throw new ApiException("Auth not found");
        }
        u.setUsername(user.getUsername());
        u.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        u.setRole("ADMIN");
        authRepository.save(u);
    }
    public void deleteAuth(Integer id)
    {
        if(authRepository.findUserById(id) == null)
        {
            throw new ApiException("Auth not found");
        }
        authRepository.deleteById(id);
    }
}
