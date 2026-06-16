package com.dmsc.back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dmsc.back_end.entity.Driver;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

    List<Driver> findByDriverCallingNameContainingIgnoreCase(String name);
}
