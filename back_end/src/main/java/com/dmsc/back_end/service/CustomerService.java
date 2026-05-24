package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.entity.Customer;

public interface CustomerService {
    
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(int id);
    Customer updateCustomer(int id, Customer customer);
    void deleteCustomer(int id);
  
}
