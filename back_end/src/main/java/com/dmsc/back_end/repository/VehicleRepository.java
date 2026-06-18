package com.dmsc.back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    List<Vehicle> findByVehicleNumberPlateContainingIgnoreCase(String plate);

    List<Vehicle> findByIsActiveTrue();
}
