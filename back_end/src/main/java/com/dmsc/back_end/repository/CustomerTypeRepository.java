package com.dmsc.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.CustomerType;


    public interface CustomerTypeRepository 
    extends JpaRepository<CustomerType, Integer> {}


