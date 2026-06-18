package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.VehicleDTO;
import com.dmsc.back_end.entity.Vehicle;
import com.dmsc.back_end.entity.VehicleModel;
import com.dmsc.back_end.entity.VehicleType;
import com.dmsc.back_end.repository.VehicleRepository;
import com.dmsc.back_end.repository.VehicleModelRepository;
import com.dmsc.back_end.repository.VehicleTypeRepository;
import com.dmsc.back_end.service.VehicleService;

import jakarta.transaction.Transactional;

@Service
public class VehicleServiceImpl implements VehicleService {

    final VehicleRepository vehicleRepository;

    @Autowired
    VehicleModelRepository vehicleModelRepository;

    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // ================= GET BY ID =================
    @Override
    public VehicleDTO getVehicleById(int id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        return toDTO(vehicle);
    }

    // ================= GET ALL =================
    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= SEARCH =================
    @Override
    public List<VehicleDTO> searchVehicle(String plate) {
        return vehicleRepository.findByVehicleNumberPlateContainingIgnoreCase(plate)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= CREATE =================
    @Override
    @Transactional
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {

        VehicleType vehicleType = vehicleTypeRepository.findById(vehicleDTO.getVehicleTypeId())
                .orElseThrow(() -> new RuntimeException("Vehicle Type not found"));

        VehicleModel vehicleModel= vehicleModelRepository.findById(vehicleDTO.getVehicleModelId())
                .orElseThrow(() -> new RuntimeException("Vehicle Model not found"));

        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleNumberPlate(vehicleDTO.getVehicleNumberPlate());
        vehicle.setCapacity(vehicleDTO.getCapacity());
        vehicle.setVehicleNote(vehicleDTO.getVehicleNote());
        vehicle.setVehicleTypeRef(vehicleType);
        vehicle.setVehicleModelRef(vehicleModel);

        vehicle.setIsActive(true);
        vehicle.setEnteredBy(vehicleDTO.getEnteredBy());
        vehicle.setEnteredDate(LocalDate.now());

        Vehicle saved = vehicleRepository.save(vehicle);
        return toDTO(saved);
    }

    // ================= UPDATE =================
    @Override
    @Transactional
    public VehicleDTO updateVehicle(int id, VehicleDTO vehicleDTO) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (vehicleDTO.getVehicleTypeId() != null) {
            VehicleType vehicleType = vehicleTypeRepository.findById(vehicleDTO.getVehicleTypeId())
                    .orElseThrow(() -> new RuntimeException("Vehicle Type not found"));
            vehicle.setVehicleTypeRef(vehicleType);
        }

        if (vehicleDTO.getVehicleModelId() != null) {
            VehicleModel vehicleModel= vehicleModelRepository.findById(vehicleDTO.getVehicleModelId())
                    .orElseThrow(() -> new RuntimeException("Vehicle Model not found"));
            vehicle.setVehicleModelRef(vehicleModel);
        }

        vehicle.setVehicleNumberPlate(vehicleDTO.getVehicleNumberPlate());
        vehicle.setCapacity(vehicleDTO.getCapacity());
        vehicle.setVehicleNote(vehicleDTO.getVehicleNote());

        vehicle.setUpdateBy(vehicleDTO.getUpdateBy());
        vehicle.setUpdateDate(LocalDate.now());

        return toDTO(vehicleRepository.save(vehicle));
    }

    // ================= DELETE (SOFT DELETE) =================
    @Override
    public void deleteVehicle(int id) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setIsActive(false);
        vehicleRepository.save(vehicle);
    }

    // ================= DTO MAPPING =================
    private VehicleDTO toDTO(Vehicle vehicle) {

        VehicleDTO vehicleDTO = new VehicleDTO();

        vehicleDTO.setVehicleId(vehicle.getVehicleId());
        vehicleDTO.setVehicleNumberPlate(vehicle.getVehicleNumberPlate());
        vehicleDTO.setCapacity(vehicle.getCapacity());
        vehicleDTO.setVehicleNote(vehicle.getVehicleNote());
        vehicleDTO.setIsActive(vehicle.getIsActive());
        vehicleDTO.setEnteredBy(vehicle.getEnteredBy());
        vehicleDTO.setEnteredDate(vehicle.getEnteredDate());
        vehicleDTO.setUpdateBy(vehicle.getUpdateBy());
        vehicleDTO.setUpdateDate(vehicle.getUpdateDate());

        if (vehicle.getVehicleTypeRef() != null) {
            vehicleDTO.setVehicleTypeId(vehicle.getVehicleTypeRef().getVehicleTypeId());
            vehicleDTO.setVehicleTypeName(vehicle.getVehicleTypeRef().getVehicleTypeName());
        }

        if (vehicle.getVehicleModelRef() != null) {
            vehicleDTO.setVehicleModelId(vehicle.getVehicleModelRef().getVehicleModelId());
            vehicleDTO.setVehicleModelName(vehicle.getVehicleModelRef().getVehicleModelName());
        }

        return vehicleDTO;
    }
}
