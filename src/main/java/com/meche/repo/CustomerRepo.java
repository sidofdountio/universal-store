package com.meche.repo;

import com.meche.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author sidof
 * @Since 20/05/2023
 */

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
