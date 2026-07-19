package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.GrnItemDTO;

public interface GrnItemService {

    GrnItemDTO createGrnItem(GrnItemDTO grnItemDTO);

    List<GrnItemDTO> getAllGrnItems();

    GrnItemDTO getGrnItemById(int id);

    List<GrnItemDTO> searchGrnItems(int id);

    List<GrnItemDTO> searchGrnItems(String itemName);

    GrnItemDTO updateGrnItem(int id, GrnItemDTO grnItemDTO);

    void deleteGrnItem(int id);

}