package com.meche.service.serviceImpl;

import com.meche.model.Customer;

import java.util.List;

/**
 * @Author sidof
 * @Since 20/05/2023
 */
public interface CustomerDao {
    Customer addCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer getCustomerById(Long customerId);

    void DeleteCustomer(Long customerIdToDelete);

    List<Customer> CUSTOMERS();
}
