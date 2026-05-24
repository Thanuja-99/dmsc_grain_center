package com.dmsc.back_end.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.entity.Customer;
import com.dmsc.back_end.repository.CustomerRepository;
import com.dmsc.back_end.service.CustomerService;

@Service
public class CustomerServiceImpl  implements CustomerService{

    @Autowired  //springboot automatically inject customerRepository into this class (call database method easy) 
    CustomerRepository customerRepository;

     @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer updateCustomer(int id, Customer customer) {

        Customer existingCustomer = customerRepository.findById(id).orElse(null);

         if(existingCustomer != null){
            existingCustomer.setCallingName(customer.getCallingName());
            existingCustomer.setCusBd(customer.getCusBd());
            existingCustomer.setCustomerNote(customer.getCustomerNote());
            existingCustomer.setActive(customer.isActive());
            existingCustomer.setEnteredBy(customer.getEnteredBy());
            existingCustomer.setEnteredDate(customer.getEnteredDate());
            existingCustomer.setUpdateBy(customer.getUpdateBy());
            existingCustomer.setUpdateDate(customer.getUpdateDate());

            existingCustomer.setCustomerType(customer.getCustomerType());
            existingCustomer.setGender(customer.getGender());
            existingCustomer.setPhoneNumbers(customer.getPhoneNumbers());

            return customerRepository.save(existingCustomer);
         }

        return null;
    }


    @Override
    public void deleteCustomer(int id) {
         customerRepository.deleteById(id);

    }


}