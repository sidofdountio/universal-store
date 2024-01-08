package com.meche.api;

import com.meche.model.Charge;
import com.meche.model.Employee;
import com.meche.service.ChargeService;
import com.meche.service.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * @Author sidof
 * @Since 28/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@RestController
@RequestMapping("/api/v1/hair/employee")
@CrossOrigin(origins = "*", maxAge = 3600,allowedHeaders = "*")@RequiredArgsConstructor
@Transactional
public class EmployeeApi {
    private final EmployeeService employeeService;
    private final ChargeService chargeService;

    @GetMapping
    public ResponseEntity<List<Employee>>getAllEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        return new ResponseEntity<List<Employee>>(employees,OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }
        return new ResponseEntity<>(employee, OK);
    }


    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee existingEmployee = employeeService.getEmployee(employee.getId());
        if (existingEmployee == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }
        Employee updatedEmployee = employeeService.update(employee);
        updateChargeTotalSalery();
        return new ResponseEntity<>(updatedEmployee,CREATED);
    }


    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = updateChargeTotalSalery(employee);
        return new ResponseEntity<>(savedEmployee,CREATED);
    }

    private void updateChargeTotalSalery() {
        Charge charge = chargeService.getCharge(1L);
        List<Employee> employees = employeeService.getEmployees();
        double employee1Salary = 0;
        for (Employee employee1: employees) {
            employee1Salary += employee1.getSalary();
        }
        charge.setTotalSalary(employee1Salary);
    }
    private Employee updateChargeTotalSalery(Employee employee) {
        Employee savedEmployee = employeeService.save(employee);
        Charge charge = chargeService.getCharge(1L);
        List<Employee> employees = employeeService.getEmployees();
        double employee1Salary = 0;
        for (Employee employee1: employees) {
            employee1Salary += employee1.getSalary();
        }
        charge.setTotalSalary(employee1Salary);
        return savedEmployee;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        Employee existingEmployee = employeeService.getEmployee(id);
        if (existingEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
