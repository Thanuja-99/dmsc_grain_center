package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.VehicleBrandDTO;

public interface VehicleBrandService {
    
    VehicleBrandDTO createBrand(VehicleBrandDTO vehicleBrandDTO);

    List<VehicleBrandDTO> getAllBrands();

    VehicleBrandDTO getBrandById(int id);

    List<VehicleBrandDTO> searchBrand(String name);

    VehicleBrandDTO updateBrand(int id, VehicleBrandDTO vehicleBrandDTO);

    void deleteBrand(int id);
}
