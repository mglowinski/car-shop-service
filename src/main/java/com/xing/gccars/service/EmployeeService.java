package com.xing.gccars.service;

import com.xing.gccars.exception.EmployeeNotFoundException;
import com.xing.gccars.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees();

    Employee saveEmployee(Employee employee);

    Employee getEmployeeById(Long id) throws EmployeeNotFoundException;

    void deleteEmployeeById(Long id) throws EmployeeNotFoundException;
}
