package com.meche.service;

import com.meche.model.Employee;
import com.meche.repo.EmployeeRepo;
import com.meche.service.serviceImpl.EmployeeDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author sidof
 * @Since 28/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService implements EmployeeDAO {
    private final EmployeeRepo employeeRepo;

    @Override
    public Employee save(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepo.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long employeeId) {
        employeeRepo.deleteById(employeeId);
    }
}
