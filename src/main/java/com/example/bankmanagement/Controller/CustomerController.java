package com.example.bankmanagement.Controller;

import com.example.bankmanagement.DTO.CustomerDTO;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v7/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/get-all-customer")
    public ResponseEntity getAllCustomer()
    {
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }
    @GetMapping("/get-my-customer")
    public ResponseEntity getMyCustomers(@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(customerService.getMyCustomer(user.getId()));
    }
    @PostMapping("/add-customer")
    public ResponseEntity RegisterCustomer(@Valid@RequestBody CustomerDTO customerDTO)
    {
        customerService.addCustomer(customerDTO);
        return ResponseEntity.status(200).body("Customer added");
    }
    @PutMapping("/update-customer/{customerID}")
    public ResponseEntity updateCustomer(@PathVariable Integer customerID,@AuthenticationPrincipal User user,@Valid@RequestBody CustomerDTO customerDTO)
    {
        customerService.updateCustomer(customerID,user.getId(),customerDTO);
        return ResponseEntity.status(200).body("Customer updated");
    }
    @DeleteMapping("/delete-customer/{customerID}")
    public ResponseEntity deleteCustomer(@PathVariable Integer customerID,@AuthenticationPrincipal User user)
    {
        customerService.deleteCustomer(customerID,user.getId());
        return ResponseEntity.status(200).body("Customer deleted");
    }
}
