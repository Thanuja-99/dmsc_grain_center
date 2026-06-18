package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.VehicleBrandDTO;
import com.dmsc.back_end.entity.VehicleBrand;
import com.dmsc.back_end.repository.VehicleBrandRepository;
import com.dmsc.back_end.service.VehicleBrandService;


@Service
public class VehicleBrandServiceImpl implements VehicleBrandService {

    @Autowired
    VehicleBrandRepository brandRepository;

    // ================= GET ALL =================
    @Override
    public List<VehicleBrandDTO> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= GET BY ID =================
    @Override
    public VehicleBrandDTO getBrandById(int id) {
        VehicleBrand vehicleBrand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        return toDTO(vehicleBrand);
    }

    // ================= SEARCH =================
    @Override
    public List<VehicleBrandDTO> searchBrand(String name) {
        return brandRepository.findByVehicleBrandNameContainingIgnoreCase(name)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= CREATE =================
    @Override
    public VehicleBrandDTO createBrand(VehicleBrandDTO vehicleBrandDTO) {

        VehicleBrand vehicleBrand = new VehicleBrand();
        vehicleBrand.setVehicleBrandName(vehicleBrandDTO.getVehicleBrandName());
        vehicleBrand.setIsActive(true);
        vehicleBrand.setEnteredBy(vehicleBrandDTO.getEnteredBy());
        vehicleBrand.setEnteredDate(LocalDate.now());

        return toDTO(brandRepository.save(vehicleBrand));
    }

    // ================= UPDATE =================
    @Override
    public VehicleBrandDTO updateBrand(int id, VehicleBrandDTO vehicleBrandDTO) {

        VehicleBrand vehicleBrand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        vehicleBrand.setVehicleBrandName(vehicleBrandDTO.getVehicleBrandName());
        vehicleBrand.setUpdateBy(vehicleBrandDTO.getUpdateBy());
        vehicleBrand.setUpdateDate(LocalDate.now());

        return toDTO(brandRepository.save(vehicleBrand));
    }

    // ================= DELETE =================
    @Override
    public void deleteBrand(int id) {

        VehicleBrand vehicleBrand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        vehicleBrand.setIsActive(false);
        brandRepository.save(vehicleBrand);
    }

    private VehicleBrandDTO toDTO(VehicleBrand vehicleBrand) {

        VehicleBrandDTO vehicleBrandDTO = new VehicleBrandDTO();

        vehicleBrandDTO.setVehicleBrandId(vehicleBrand.getVehicleBrandId());
        vehicleBrandDTO.setVehicleBrandName(vehicleBrand.getVehicleBrandName());
        vehicleBrandDTO.setIsActive(vehicleBrand.getIsActive());

        return vehicleBrandDTO;
    }
}
