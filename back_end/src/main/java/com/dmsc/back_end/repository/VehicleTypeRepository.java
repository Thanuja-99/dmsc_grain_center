package com.dmsc.back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.VehicleType;

public interface VehicleTypeRepository extends JpaRepository <VehicleType, Integer> {
    List<VehicleType> findByVehicleTypeNameContainingIgnoreCase(String name);
}
