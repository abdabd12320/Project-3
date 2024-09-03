package com.example.bankmanagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "accountNumber should not be null")
    @Column(columnDefinition = "VARCHAR(19) NOT NULL")
    private String accountNumber;
    @NotNull(message = "balance should not be null")
    @Column(columnDefinition = "INT NOT NULL")
    private int balance;
    @AssertFalse
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean active;

    @ManyToOne
    @JsonIgnore
    private Customer customer;
}
