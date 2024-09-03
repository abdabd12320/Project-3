package com.example.bankmanagement.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

//    private Integer user_id;

    @NotEmpty(message = "username should not be empty")
    @Size(min = 4,max = 10,message = "username should between 4 and 10 chars")
//    @Column(columnDefinition = "VARCHAR(10) NOT NULL UNiQUE")
    private String username;
    @NotEmpty(message = "password should not be empty")
    @Size(min = 6,message = "password should at least 6 chars")
//    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String password;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 2,max = 10,message = "name should between 2 and 10 chars")
//    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;
    @NotEmpty(message = "email should not be empty")
    @Email
//    @Column(columnDefinition = "VARCHAR(20) NOT NULL UNiQUE")
    private String email;
    @NotEmpty(message = "role should not be empty")
    @Pattern(regexp = "ADMIN|CUSTOMER|EMPLOYEE")
//    @Column(columnDefinition = "VARCHAR(8) NOT NULL")
    private String role;

    @NotEmpty(message = "phoneNumber should not be empty")
    @Pattern(regexp = "^(05)(\\d){8}$",message = "phoneNumber must to start with 05")
    private String phoneNumber;
}
