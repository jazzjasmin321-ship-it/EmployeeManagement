package com.example.employeemanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employeemanagement.dto.EmployeeRequest;
import com.example.employeemanagement.dto.EmployeeResponse;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Create an employee
    public EmployeeResponse createEmployee(EmployeeRequest request) {

        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "An employee with this email already exists");
        }

        Employee employee = new Employee(
                request.getFullName(),
                request.getEmail(),
                request.getDepartment(),
                request.getDesignation(),
                request.getSalary()
        );

        Employee savedEmployee = employeeRepository.save(employee);

        return convertToResponse(savedEmployee);
    }

    // View all employees
    @Transactional(readOnly = true)
    public List<EmployeeResponse> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // View an employee by ID
    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with ID: " + id));

        return convertToResponse(employee);
    }

    // Update an employee
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with ID: " + id));

        if (employeeRepository.existsByEmail(request.getEmail())
                && !employee.getEmail().equalsIgnoreCase(request.getEmail())) {
            throw new IllegalArgumentException(
                    "An employee with this email already exists");
        }

        employee.setFullName(request.getFullName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());
        employee.setDesignation(request.getDesignation());
        employee.setSalary(request.getSalary());

        Employee updatedEmployee = employeeRepository.save(employee);

        return convertToResponse(updatedEmployee);
    }

    // Delete an employee
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with ID: " + id));

        employeeRepository.delete(employee);
    }

    // Convert Entity to Response DTO
    private EmployeeResponse convertToResponse(Employee employee) {

        return new EmployeeResponse(
                employee.getId(),
                employee.getFullName(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getDesignation(),
                employee.getSalary()
        );
    }
}