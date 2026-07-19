package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.GrnDTO;

public interface GrnService {

    GrnDTO createGrn(GrnDTO grnDTO);

    List<GrnDTO> getAllGrns();

    GrnDTO getGrnById(int id);

    List<GrnDTO> searchGrns(int id);

    List<GrnDTO> searchGrns(String note);

    GrnDTO updateGrn(int id, GrnDTO grnDTO);

    void deleteGrn(int id);

}