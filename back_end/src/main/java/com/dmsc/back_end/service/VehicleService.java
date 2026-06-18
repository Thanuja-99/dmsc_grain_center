package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.VehicleDTO;

public interface VehicleService {
    
    VehicleDTO createVehicle(VehicleDTO vehicleDTO);

    List<VehicleDTO> getAllVehicles();

    VehicleDTO getVehicleById(int id);

    List<VehicleDTO> searchVehicle(String plate);

    VehicleDTO updateVehicle(int id, VehicleDTO vehicleDTO);

    void deleteVehicle(int id);
}
