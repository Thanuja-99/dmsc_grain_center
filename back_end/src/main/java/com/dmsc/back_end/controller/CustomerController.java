package com.dmsc.back_end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dmsc.back_end.entity.Customer;
import com.dmsc.back_end.service.CustomerService;

@RestController
@RequestMapping("api/customers")
@CrossOrigin
public class CustomerController {
    

    @Autowired
    CustomerService customerService;

    //create Customer
    @PostMapping
    public Customer createCustomer( @RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    //get all customers
    @GetMapping
    public List<Customer> getALLCustomer(){
        return customerService.getAllCustomers();
    }

    //get customer by id
    @GetMapping("/{id}")
    public Customer getCustomerByid(@PathVariable int id){
        return customerService.getCustomerById(id);
    }

    //update Customer
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customer){
        return customerService.updateCustomer(id, customer);
    }
    //delete Customer
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id){
        customerService.deleteCustomer (id);
    }
    @GetMapping("/test")
public String test() {
    return "API working";
}
}
