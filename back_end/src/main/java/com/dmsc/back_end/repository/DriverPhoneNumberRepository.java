package com.dmsc.back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.DriverPhoneNumber;

public interface DriverPhoneNumberRepository extends JpaRepository<DriverPhoneNumber,Integer >{
    
    List<DriverPhoneNumber> findByDriver_DriverId(Integer driverId);
    void deleteByDriver_DriverId(Integer driverId);
}
