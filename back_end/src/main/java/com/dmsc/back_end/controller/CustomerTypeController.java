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

import com.dmsc.back_end.entity.CustomerType;
import com.dmsc.back_end.service.CustomerTypeService;

@RestController
@RequestMapping("/api/customer-types")
@CrossOrigin
public class CustomerTypeController {


    @Autowired
    private CustomerTypeService customerTypeService;

    
    @PostMapping
    public CustomerType createCustomerType(@RequestBody CustomerType customerType) {
        return customerTypeService.createCustomerType(customerType);
    }

    @GetMapping
    public List<CustomerType> getAllCustomerTypes() {
        return customerTypeService.getAllCustomerTypes();
    }
    
    @GetMapping("/{id}")
    public CustomerType getCustomerTypeById(@PathVariable int id) {
        return customerTypeService.getCustomerTypeById(id);
    }

    @PutMapping("/{id}")
    public CustomerType updateCustomerType(@PathVariable int id, CustomerType customerType){
        return customerTypeService.updateCustomerType(id, customerType);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteCustomerType(@PathVariable int id){
        customerTypeService.deleteCustomerType(id);
    }
}
