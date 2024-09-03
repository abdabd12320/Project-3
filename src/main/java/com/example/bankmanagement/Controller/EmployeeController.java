package com.example.bankmanagement.Controller;

import com.example.bankmanagement.DTO.EmployeeDTO;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v7/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/get-all")
    public ResponseEntity getAllEmployees()
    {
        return ResponseEntity.status(200).body(employeeService.getAllEmployee());
    }
    @GetMapping("/get-my")
    public ResponseEntity getMyEmployees(@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(employeeService.getMyEmployee(user.getId()));
    }
    @PostMapping("/add-employee")
    public ResponseEntity registerEmployee(@Valid @RequestBody EmployeeDTO employeeDTO)
    {
        employeeService.addEmployee(employeeDTO);
        return ResponseEntity.status(200).body("Employee added");
    }
    @PutMapping("/update-employee/{employeeID}")
    public ResponseEntity updateEmployee(@PathVariable Integer employeeID,@AuthenticationPrincipal User user,@Valid @RequestBody EmployeeDTO employeeDTO)
    {
        employeeService.updateEmployee(employeeID, user.getId(), employeeDTO);
        return ResponseEntity.status(200).body("Employee updated");
    }
    @DeleteMapping("/delete-employee/{employeeID}")
    public ResponseEntity deleteEmployee(@PathVariable Integer employeeID,@AuthenticationPrincipal User user)
    {
        employeeService.deleteEmployee(employeeID, user.getId());
        return ResponseEntity.status(200).body("Employee deleted");
    }
}
