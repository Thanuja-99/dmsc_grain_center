package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.GenderDTO;


public interface GenderService {
     List<GenderDTO> getAllGenders();
    
    GenderDTO getGenderById(int id);
    
    GenderDTO createGender(GenderDTO dto);
    
    GenderDTO updateGender(int id, GenderDTO dto);
    
    void deleteGender(int id);
}
