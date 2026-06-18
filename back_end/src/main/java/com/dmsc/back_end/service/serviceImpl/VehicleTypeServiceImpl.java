package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.VehicleTypeDTO;
import com.dmsc.back_end.entity.VehicleType;
import com.dmsc.back_end.repository.VehicleTypeRepository;
import com.dmsc.back_end.service.VehicleTypeService;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {

    @Autowired
    VehicleTypeRepository typeRepository;

    @Override
    public List<VehicleTypeDTO> getAllTypes() {
        return typeRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleTypeDTO getTypeById(int id) {
        return toDTO(typeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type not found")));
    }

    @Override
    public List<VehicleTypeDTO> searchType(String name) {
        return typeRepository.findByVehicleTypeNameContainingIgnoreCase(name)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleTypeDTO createType(VehicleTypeDTO dto) {

        VehicleType vehicleType = new VehicleType();
        vehicleType.setVehicleTypeName(dto.getVehicleTypeName());
        vehicleType.setIsActive(true);
        vehicleType.setEnteredBy(dto.getEnteredBy());
        vehicleType.setEnteredDate(LocalDate.now());

        return toDTO(typeRepository.save(vehicleType));
    }

    @Override
    public VehicleTypeDTO updateType(int id, VehicleTypeDTO vehicleTypeDTO) {

        VehicleType vehicleType = typeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type not found"));

        vehicleType.setVehicleTypeName(vehicleTypeDTO.getVehicleTypeName());
        vehicleType.setUpdateBy(vehicleTypeDTO.getUpdateBy());
        vehicleType.setUpdateDate(LocalDate.now());

        return toDTO(typeRepository.save(vehicleType));
    }

    @Override
    public void deleteType(int id) {

        VehicleType vehicleType = typeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type not found"));

        vehicleType.setIsActive(false);
        typeRepository.save(vehicleType);
    }

    private VehicleTypeDTO toDTO(VehicleType vehicleType) {

        VehicleTypeDTO vehicleTypeDTO = new VehicleTypeDTO();

        vehicleTypeDTO.setVehicleTypeId(vehicleType.getVehicleTypeId());
        vehicleTypeDTO.setVehicleTypeName(vehicleType.getVehicleTypeName());
        vehicleTypeDTO.setIsActive(vehicleType.getIsActive());

        return vehicleTypeDTO;
    }
}
