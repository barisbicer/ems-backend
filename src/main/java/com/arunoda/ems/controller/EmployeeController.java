package com.arunoda.ems.controller;

import com.arunoda.ems.dto.EmployeeDTO;
import com.arunoda.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    //Build Add Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            EmployeeDTO savedEmployee = employeeService.createEmployee(employeeDTO);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Build Get Employee REST API
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long employeeId) {
        Optional<EmployeeDTO> employeeDTO = Optional.ofNullable(employeeService.getEmployeeById(employeeId));
        return employeeDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

   //Build Get All Employees REST API
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        try {
            List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();
            return ResponseEntity.ok(allEmployees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //Build Update Employee REST API
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable("id") Long employeeId,
            @RequestBody EmployeeDTO updatedEmployee) {

        if (updatedEmployee == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            EmployeeDTO employeeDTO = employeeService.updateEmployee(employeeId, updatedEmployee);
            if (employeeDTO == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(employeeDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Build Delete Employee REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId) {
        try {
            employeeService.deleteEmployee(employeeId);
            return ResponseEntity.ok("Employee with ID: " + employeeId + " deleted successfully.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
