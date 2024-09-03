package com.example.bankmanagement.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "username should not be empty")
    @Size(min = 4,max = 10,message = "username should between 4 and 10 chars")
    @Column(columnDefinition = "VARCHAR(10) NOT NULL UNiQUE")
    private String username;
    @NotEmpty(message = "password should not be empty")
    @Size(min = 6,message = "password should at least 6 chars")
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String password;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 2,max = 10,message = "name should between 2 and 10 chars")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;
    @NotEmpty(message = "email should not be empty")
    @Email
    @Column(columnDefinition = "VARCHAR(20) NOT NULL UNiQUE")
    private String email;
    @NotEmpty(message = "role should not be empty")
    @Pattern(regexp = "ADMIN|CUSTOMER|EMPLOYEE")
    @Column(columnDefinition = "VARCHAR(10) NOT NULL")
    private String role;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer employee;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
