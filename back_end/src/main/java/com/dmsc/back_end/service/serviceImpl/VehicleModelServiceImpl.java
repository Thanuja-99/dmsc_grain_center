package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.VehicleModelDTO;
import com.dmsc.back_end.entity.VehicleModel;
import com.dmsc.back_end.repository.VehicleModelRepository;
import com.dmsc.back_end.service.VehicleModelService;

@Service
public class VehicleModelServiceImpl implements VehicleModelService {

    @Autowired
    VehicleModelRepository modelRepository;

    @Override
    public List<VehicleModelDTO> getAllModels() {
        return modelRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleModelDTO getModelById(int id) {
        return toDTO(modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found")));
    }

    @Override
    public List<VehicleModelDTO> searchModel(String name) {
        return modelRepository.findByVehicleModelNameContainingIgnoreCase(name)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleModelDTO createModel(VehicleModelDTO vehicleModelDTO) {

        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setVehicleModelName(vehicleModelDTO.getVehicleModelName());
        vehicleModel.setIsActive(true);
        vehicleModel.setEnteredBy(vehicleModelDTO.getEnteredBy());
        vehicleModel.setEnteredDate(LocalDate.now());

        return toDTO(modelRepository.save(vehicleModel));
    }

    @Override
    public VehicleModelDTO updateModel(int id, VehicleModelDTO vehicleModelDTO) {

        VehicleModel vehicleModel = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found"));

        vehicleModel.setVehicleModelName(vehicleModelDTO.getVehicleModelName());
        vehicleModel.setUpdateBy(vehicleModelDTO.getUpdateBy());
        vehicleModel.setUpdateDate(LocalDate.now());

        return toDTO(modelRepository.save(vehicleModel));
    }

    @Override
    public void deleteModel(int id) {

        VehicleModel vehicleModel = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found"));

        vehicleModel.setIsActive(false);
        modelRepository.save(vehicleModel);
    }

    private VehicleModelDTO toDTO(VehicleModel vehicleModel) {

        VehicleModelDTO vehicleModelDTO = new VehicleModelDTO();

        vehicleModelDTO.setVehicleModelId(vehicleModel.getVehicleModelId());
        vehicleModelDTO.setVehicleModelName(vehicleModel.getVehicleModelName());
        vehicleModelDTO.setIsActive(vehicleModel.getIsActive());

        return vehicleModelDTO;
    }
}
