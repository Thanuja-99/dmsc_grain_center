package com.dmsc.back_end.service;

import java.util.List;


import com.dmsc.back_end.dto.DriverTypeDTO;

public interface DriverTypeService {

    List<DriverTypeDTO> getAllDriverTypes();
    
    DriverTypeDTO getDriverTypeById(int id);
    
    DriverTypeDTO saveDriverType(DriverTypeDTO driverTypeDTO);
    
    DriverTypeDTO updateDriverType(int id, DriverTypeDTO driverTypeDTO);
    
    void deleteDriverType(int id);
    
}
