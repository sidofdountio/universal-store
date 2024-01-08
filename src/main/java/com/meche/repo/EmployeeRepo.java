package com.meche.repo;

import com.meche.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author sidof
 * @Since 28/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
}
