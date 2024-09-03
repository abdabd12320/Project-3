package com.example.bankmanagement.Service;

import com.example.bankmanagement.Api.ApiException;
import com.example.bankmanagement.DTO.EmployeeDTO;
import com.example.bankmanagement.Model.Employee;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Repository.AuthRepository;
import com.example.bankmanagement.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;

    public List<Employee> getAllEmployee()
    {
        return employeeRepository.findAll();
    }
    public List<Employee> getMyEmployee(Integer id)
    {
        return employeeRepository.findAllByUser(authRepository.findUserById(id));
    }
    public void addEmployee(EmployeeDTO employeeDTO)
    {
        User user1 = new User();
        user1.setUsername(employeeDTO.getUsername());
        user1.setPassword(employeeDTO.getPassword());
        user1.setName(employeeDTO.getName());
        user1.setEmail(employeeDTO.getEmail());
        user1.setRole(employeeDTO.getRole());
        user1.setPassword(new BCryptPasswordEncoder().encode(user1.getPassword()));

        Employee e = new Employee();
        e.setId(user1.getId());
        e.setPosition(employeeDTO.getPosition());
        e.setSalary(employeeDTO.getSalary());
        e.setUser(user1);
       // user1.setEmployee(e);
        authRepository.save(user1);
        employeeRepository.save(e);
    }

    public void updateEmployee(Integer employeeID,Integer userID,EmployeeDTO employeeDTO)
    {
        Employee e = employeeRepository.findEmployeeById(employeeID);

        if(e == null)
        {
            throw new ApiException("Customer not found");
        }
        if(authRepository.findUserById(userID) == null)
        {
            throw new ApiException("User not found");
        }
        if(employeeID != authRepository.findUserById(userID).getEmployee().getId())
        {
            throw new ApiException("not match");
        }
        e.setPosition(employeeDTO.getPosition());
        e.setSalary(employeeDTO.getSalary());
        e.setUser(authRepository.findUserById(userID));
        employeeRepository.save(e);
    }
    public void deleteEmployee(Integer employeeID,Integer userID)
    {
        if(employeeRepository.findEmployeeById(employeeID) == null)
        {
            throw new ApiException("Customer not found");
        }
        if(authRepository.findUserById(userID) == null)
        {
            throw new ApiException("User not found");
        }
        if(employeeID != authRepository.findUserById(userID).getEmployee().getId())
        {
            throw new ApiException("not match");
        }
        employeeRepository.delete(employeeRepository.findEmployeeById(employeeID));
    }
}
