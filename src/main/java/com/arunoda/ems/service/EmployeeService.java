package com.arunoda.ems.service;

import com.arunoda.ems.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    //Create Employee
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    //Search Employee By Id
    EmployeeDTO getEmployeeById(Long employeeId);

    //Get All Employees
    List<EmployeeDTO> getAllEmployees();




}
