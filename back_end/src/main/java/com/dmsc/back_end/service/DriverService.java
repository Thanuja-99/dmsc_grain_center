package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.DriverDTO;

public interface DriverService {


    DriverDTO createDriver(DriverDTO driverDTO);
    List<DriverDTO> getAllDrivers();
    DriverDTO getDriverById(int id);
    List<DriverDTO> searchDrivers(int id);
    List<DriverDTO> searchDrivers(String name);
    DriverDTO updateDriver(int id, DriverDTO driverDTO);
    void deleteDriver(int id);
}
