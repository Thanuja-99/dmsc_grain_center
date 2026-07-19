package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.GrnStatusDTO;

public interface GrnStatusService {

    GrnStatusDTO createGrnStatus(GrnStatusDTO grnStatusDTO);

    List<GrnStatusDTO> getAllGrnStatuses();

    GrnStatusDTO getGrnStatusById(int id);

    List<GrnStatusDTO> searchGrnStatuses(int id);

    List<GrnStatusDTO> searchGrnStatuses(String name);

    GrnStatusDTO updateGrnStatus(int id, GrnStatusDTO grnStatusDTO);

    void deleteGrnStatus(int id);

}