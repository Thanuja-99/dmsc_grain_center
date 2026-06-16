package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.entity.Driver;
import com.dmsc.back_end.dto.DriverPhoneNumberDTO;
import com.dmsc.back_end.entity.DriverPhoneNumber;
import com.dmsc.back_end.repository.DriverPhoneNumberRepository;
import com.dmsc.back_end.repository.DriverRepository;
import com.dmsc.back_end.service.DriverPhoneNumberService;



@Service
public class DriverPhoneNumberServiceImpl implements DriverPhoneNumberService {

    @Autowired
    DriverPhoneNumberRepository driverPhoneNumberRepository;

    @Autowired
    DriverRepository driverRepository;


     @Override
    public List<DriverPhoneNumberDTO> getAllPhoneNumbers() {
        return driverPhoneNumberRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DriverPhoneNumberDTO> getPhoneNumbersByDriverId(int driverId) {
        return driverPhoneNumberRepository.findByDriver_DriverId(driverId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DriverPhoneNumberDTO createPhoneNumber(DriverPhoneNumberDTO driverPhoneNumberDTO) {
        Driver driver = driverRepository.findById(driverPhoneNumberDTO.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        
        DriverPhoneNumber phone = new DriverPhoneNumber();
        phone.setDriver(driver);
        phone.setDriverPhoneNumber(driverPhoneNumberDTO.getDriverPhoneNumber());
        phone.setActive(true);
        phone.setEnteredDate(LocalDate.now());
        phone.setDriver(driver);
        
        DriverPhoneNumber saved = driverPhoneNumberRepository.save(phone);
        return toDTO(saved);
    }

    @Override
    public DriverPhoneNumberDTO updatePhoneNumber(int id, DriverPhoneNumberDTO driverPhoneNumberDTO) {
        DriverPhoneNumber phone = driverPhoneNumberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Phone number not found"));
        phone.setDriverPhoneNumber(driverPhoneNumberDTO.getDriverPhoneNumber());
        phone.setActive(driverPhoneNumberDTO.getIsActive());
        phone.setUpdateDate(LocalDate.now());
        DriverPhoneNumber updated = driverPhoneNumberRepository.save(phone);
        return toDTO(updated);
    }

    @Override
    public void deletePhoneNumber(int id) {
        driverPhoneNumberRepository.deleteById(id);
    }

    private DriverPhoneNumberDTO toDTO(DriverPhoneNumber phone) {
        DriverPhoneNumberDTO dto = new DriverPhoneNumberDTO();
        dto.setDriverPhoneNumberId(phone.getDriverPhoneNumberId());
        dto.setDriverPhoneNumber(phone.getDriverPhoneNumber());
        dto.setIsActive(phone.isActive());
        dto.setDriverId(phone.getDriver() != null ? phone.getDriver().getDriverId() : null);
        return dto;
    }

    
}
