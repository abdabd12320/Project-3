package com.example.bankmanagement.Controller;

import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Repository.AuthRepository;
import com.example.bankmanagement.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v7/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/get-all")
    public ResponseEntity getAllAuths()
    {
        return ResponseEntity.status(200).body(authService.getAllAuths());
    }
    @PostMapping("/register")
    public ResponseEntity register(@Valid@RequestBody User user)
    {
        authService.register(user);
        return ResponseEntity.status(200).body("Registered");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@Valid@RequestBody User user)
    {
        authService.updateAuth(id, user);
        return ResponseEntity.status(200).body("User updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id)
    {
        authService.deleteAuth(id);
        return ResponseEntity.status(200).body("User deleted");
    }
}
