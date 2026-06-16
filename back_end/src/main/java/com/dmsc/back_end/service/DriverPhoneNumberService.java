package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.DriverPhoneNumberDTO;

public interface DriverPhoneNumberService {
    List<DriverPhoneNumberDTO> getAllPhoneNumbers();

    List<DriverPhoneNumberDTO> getPhoneNumbersByDriverId(int driverId);

    DriverPhoneNumberDTO createPhoneNumber(DriverPhoneNumberDTO driverPhoneNumberDTO);

    DriverPhoneNumberDTO updatePhoneNumber(int id, DriverPhoneNumberDTO driverPhoneNumberDTO);

    void deletePhoneNumber(int id);
}
