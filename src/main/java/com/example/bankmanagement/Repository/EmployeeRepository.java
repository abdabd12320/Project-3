package com.example.bankmanagement.Repository;

import com.example.bankmanagement.Model.Employee;
import com.example.bankmanagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee findEmployeeById(Integer id);

    List<Employee> findAllByUser(User user);
}
