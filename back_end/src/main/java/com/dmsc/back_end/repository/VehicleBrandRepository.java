package com.dmsc.back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.VehicleBrand;

public interface VehicleBrandRepository extends JpaRepository<VehicleBrand, Integer> {
    
    List<VehicleBrand> findByVehicleBrandNameContainingIgnoreCase(String name);
}
