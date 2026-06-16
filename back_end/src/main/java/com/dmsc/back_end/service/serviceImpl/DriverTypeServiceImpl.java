package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.DriverTypeDTO;
import com.dmsc.back_end.entity.DriverType;
import com.dmsc.back_end.repository.DriverTypeRepository;
import com.dmsc.back_end.service.DriverTypeService;


@Service
public class DriverTypeServiceImpl implements DriverTypeService {
    @Autowired
    DriverTypeRepository driverTypeRepository;

    @Override
    public List<DriverTypeDTO> getAllDriverTypes() {
        return driverTypeRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DriverTypeDTO getDriverTypeById(int id) {
        DriverType dt = driverTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver Type not found"));
        return toDTO(dt);
    }

    @Override
    public DriverTypeDTO saveDriverType(DriverTypeDTO driverTypeDTO) {
        DriverType dt = new DriverType();
        dt.setDriverTypeName(driverTypeDTO.getDriverTypeName());
        DriverType saved = driverTypeRepository.save(dt);
        return toDTO(saved);
    }

    @Override
    public DriverTypeDTO updateDriverType(int id, DriverTypeDTO driverTypeDTO) {
        DriverType dt = driverTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver Type not found"));
        dt.setDriverTypeName(driverTypeDTO.getDriverTypeName());
        DriverType updated = driverTypeRepository.save(dt);
        return toDTO(updated);
    }

    @Override
    public void deleteDriverType(int id) {
        driverTypeRepository.deleteById(id);
    }

    private DriverTypeDTO toDTO(DriverType dt) {
        DriverTypeDTO driverTypeDTO = new DriverTypeDTO();
        driverTypeDTO.setDriverTypeId(dt.getDriverTypeId());
        driverTypeDTO.setDriverTypeName(dt.getDriverTypeName());

        return driverTypeDTO;
    }
}
