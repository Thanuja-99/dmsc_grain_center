package com.dmsc.back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.VehicleModel;

public interface VehicleModelRepository extends JpaRepository<VehicleModel, Integer> {
    
    List<VehicleModel> findByVehicleModelNameContainingIgnoreCase(String name);
}
