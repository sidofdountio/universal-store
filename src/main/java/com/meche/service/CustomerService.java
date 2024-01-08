package com.meche.service;

import com.meche.model.Customer;
import com.meche.repo.CustomerRepo;
import com.meche.service.serviceImpl.CustomerDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author sidof
 * @Since 20/05/2023
 * @Version v1.0
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CustomerService implements CustomerDao {
    private final CustomerRepo customerRepo;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepo.findById(customerId).orElseThrow();
    }

    @Override
    public void DeleteCustomer(Long customerIdToDelete) {
        customerRepo.deleteById(customerIdToDelete);
    }

    @Override
    public List<Customer> CUSTOMERS() {
        return customerRepo.findAll();
    }
}
