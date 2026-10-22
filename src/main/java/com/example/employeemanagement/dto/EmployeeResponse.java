package com.example.employeemanagement.dto;

import java.math.BigDecimal;

public class EmployeeResponse {

    private Long id;
    private String fullName;
    private String email;
    private String department;
    private String designation;
    private BigDecimal salary;

    public EmployeeResponse() {
    }

    public EmployeeResponse(Long id, String fullName, String email,
                            String department, String designation,
                            BigDecimal salary) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}