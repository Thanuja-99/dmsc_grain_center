package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.DriverDTO;
import com.dmsc.back_end.entity.Driver;
import com.dmsc.back_end.entity.DriverPhoneNumber;
import com.dmsc.back_end.entity.DriverType;
import com.dmsc.back_end.entity.Gender;
import com.dmsc.back_end.repository.DriverPhoneNumberRepository;
import com.dmsc.back_end.repository.DriverRepository;
import com.dmsc.back_end.repository.DriverTypeRepository;
import com.dmsc.back_end.repository.GenderRepository;
import com.dmsc.back_end.service.DriverService;

import jakarta.transaction.Transactional;

@Service

public class DriverServiceImpl implements DriverService {
    @Autowired
    DriverRepository driverRepository;

    @Autowired
    DriverTypeRepository driverTypeRepository;

    @Autowired
    GenderRepository genderRepository;

    @Autowired
    DriverPhoneNumberRepository phoneRepository;

    // ================= GET BY ID =================
    @Override
    public DriverDTO getDriverById(int id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        return toDTO(driver);
    }

    @Override
    public List<DriverDTO> getAllDrivers() {
    return driverRepository.findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
}

 // ================= SEARCH (simple by ID or replace later) =================
    @Override
    public List<DriverDTO> searchDrivers(int id) {
        return driverRepository.findById(id)
                .map(driver -> List.of(toDTO(driver)))
                .orElse(new ArrayList<>());
    }
    
    @Override
    public List<DriverDTO> searchDrivers(String name) {
        return driverRepository.findByDriverCallingNameContainingIgnoreCase(name).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    // ================== CREATE ========================
    @Override
    @Transactional
    public DriverDTO createDriver(DriverDTO dto){

        DriverType driverType = driverTypeRepository.findById(dto.getDriverTypeId())
                .orElseThrow(() -> new RuntimeException("Driver Type not found"));

        Gender gender = genderRepository.findById(dto.getGenderId())
                .orElseThrow(() -> new RuntimeException("Gender not found"));

                Driver driver = new Driver();
                driver.setDriverCallingName(dto.getDriverCallingName());
                driver.setDriverBd(dto.getDriverBd());
                driver.setDriverNote(dto.getDriverNote());
                driver.setActive(true);
                driver.setEnteredBy(dto.getEnteredBy());
                driver.setEnteredDate(LocalDate.now());
                driver.setDriverType(driverType);
                driver.setGender(gender);
                driver.setPhoneNumbers(new ArrayList<>());

                Driver savedDriver = driverRepository.save(driver);

                // save phone numbers
        if (dto.getPhoneNumbers() != null) {
            for (String phoneNum : dto.getPhoneNumbers()) {
                DriverPhoneNumber phone = new DriverPhoneNumber();
                phone.setDriverPhoneNumber(phoneNum);
                phone.setActive(true);
                phone.setEnteredDate(LocalDate.now());
                phone.setDriver(savedDriver);
                phoneRepository.save(phone);
            }
        }
         return toDTO(savedDriver);

    }


    // ================= UPDATE =================
    @Override
    @Transactional
    public DriverDTO updateDriver(int id, DriverDTO dto) {

        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        if (dto.getDriverTypeId() != null) {
            DriverType driverType = driverTypeRepository.findById(dto.getDriverTypeId())
                    .orElseThrow(() -> new RuntimeException("Driver Type not found"));
            driver.setDriverType(driverType);
        }

        if (dto.getGenderId() != null) {
            Gender gender = genderRepository.findById(dto.getGenderId())
                    .orElseThrow(() -> new RuntimeException("Gender not found"));
            driver.setGender(gender);
        }

        driver.setDriverCallingName(dto.getDriverCallingName());
        driver.setDriverBd(dto.getDriverBd());
        driver.setDriverNote(dto.getDriverNote());

        if (dto.getIsActive() != null) {
            driver.setActive(dto.getIsActive());
        }

        driver.setUpdateBy(dto.getUpdateBy());
        driver.setUpdateDate(LocalDate.now());

        // remove old phones (simple way)
        phoneRepository.deleteByDriver_DriverId(id);

        // add new phones
        if (dto.getPhoneNumbers() != null) {
            for (String phoneNum : dto.getPhoneNumbers()) {
                DriverPhoneNumber phone = new DriverPhoneNumber();
                phone.setDriverPhoneNumber(phoneNum);
                phone.setActive(true);
                phone.setEnteredDate(LocalDate.now());
                phone.setDriver(driver);
                phoneRepository.save(phone);
            }
        }

        Driver updated = driverRepository.save(driver);
        return toDTO(updated);
    }

// ================= DELETE (soft delete) =================
    @Override
    public void deleteDriver(int id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        driver.setActive(false);
        driverRepository.save(driver);
    }


      // ================= ENTITY → DTO =================
    private DriverDTO toDTO(Driver driver) {
        DriverDTO dto = new DriverDTO();

        dto.setDriverId(driver.getDriverId());
        dto.setDriverCallingName(driver.getDriverCallingName());
        dto.setDriverBd(driver.getDriverBd());
        dto.setDriverNote(driver.getDriverNote());
        dto.setIsActive(driver.isActive());
        dto.setEnteredBy(driver.getEnteredBy());
        dto.setEnteredDate(driver.getEnteredDate());
        dto.setUpdateBy(driver.getUpdateBy());
        dto.setUpdateDate(driver.getUpdateDate());

        if (driver.getDriverType() != null) {
            dto.setDriverTypeId(driver.getDriverType().getDriverTypeId());
            dto.setDriverTypeName(driver.getDriverType().getDriverTypeName());
        }

        if (driver.getGender() != null) {
            dto.setGenderId(driver.getGender().getGenderId());
            dto.setGenderName(driver.getGender().getGenderName());
        }

        if (driver.getPhoneNumbers() != null) {
            dto.setPhoneNumbers(
                    driver.getPhoneNumbers()
                            .stream()
                            .map(DriverPhoneNumber::getDriverPhoneNumber)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

}
