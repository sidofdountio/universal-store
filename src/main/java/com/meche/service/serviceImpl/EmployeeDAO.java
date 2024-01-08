package com.meche.service.serviceImpl;

import com.meche.model.Employee;

import java.util.List;

/**
 * @Author sidof
 * @Since 28/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
public interface EmployeeDAO {
    Employee save(Employee employee);

    Employee update(Employee employee);

    List<Employee> getEmployees();

    Employee getEmployee(Long id);

    void delete(Long employeeId);
}
