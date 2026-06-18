package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.VehicleTypeDTO;

public interface VehicleTypeService {
    
    VehicleTypeDTO createType(VehicleTypeDTO vehicleTypeDTOdto);

    List<VehicleTypeDTO> getAllTypes();

    VehicleTypeDTO getTypeById(int id);

    List<VehicleTypeDTO> searchType(String name);

    VehicleTypeDTO updateType(int id, VehicleTypeDTO vehicleTypeDTO);

    void deleteType(int id);
}
