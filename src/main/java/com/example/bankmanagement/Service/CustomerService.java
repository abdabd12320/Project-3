package com.example.bankmanagement.Service;

import com.example.bankmanagement.Api.ApiException;
import com.example.bankmanagement.DTO.CustomerDTO;
import com.example.bankmanagement.Model.Customer;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Repository.AuthRepository;
import com.example.bankmanagement.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    public List<Customer> getAllCustomers()
    {
        return customerRepository.findAll();
    }

    public List<Customer> getMyCustomer(Integer id)
    {
        return customerRepository.findAllByUser(authRepository.findUserById(id));
    }

    public void addCustomer(CustomerDTO customerDTO)
    {
        User user1 = new User(null,customerDTO.getUsername(), customerDTO.getPassword(), customerDTO.getName(), customerDTO.getEmail(), customerDTO.getRole(), null,null);
        user1.setPassword(new BCryptPasswordEncoder().encode(user1.getPassword()));
        Customer c = new Customer(user1.getId(), customerDTO.getPhoneNumber(),user1,null);
//        user1.setCustomer(c);
        c.setUser(user1);
        authRepository.save(user1);
        customerRepository.save(c);
    }
    public void updateCustomer(Integer customerID,Integer userID,CustomerDTO customerDTO)
    {
        Customer c = customerRepository.findCustomerById(customerID);
        User u = authRepository.findUserById(userID);
        if(c == null)
        {
            throw new ApiException("Customer not found");
        }
        if(u == null)
        {
            throw new ApiException("User not found");
        }
        if(customerID != u.getCustomer().getId())
        {
            throw new ApiException("not match");
        }
        c.setPhoneNumber(c.getPhoneNumber());
        c.setUser(u);
        customerRepository.save(c);
    }
    public void deleteCustomer(Integer customerID,Integer userID)
    {
        User u = authRepository.findUserById(userID);
        if(customerRepository.findCustomerById(customerID) == null)
        {
            throw new ApiException("Customer not found");
        }
        if(u == null)
        {
            throw new ApiException("User not found");
        }
        if(customerID != u.getCustomer().getId())
        {
            throw new ApiException("not match");
        }
        customerRepository.deleteById(customerID);
    }
}
