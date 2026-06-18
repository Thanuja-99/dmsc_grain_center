package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.VehicleModelDTO;

public interface VehicleModelService {
    
    VehicleModelDTO createModel(VehicleModelDTO vehicleModelDTO);

    List<VehicleModelDTO> getAllModels();

    VehicleModelDTO getModelById(int id);

    List<VehicleModelDTO> searchModel(String name);

    VehicleModelDTO updateModel(int id, VehicleModelDTO vehicleModelDTO);

    void deleteModel(int id);
}
