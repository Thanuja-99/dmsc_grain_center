package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.PurchaseOrderItemDTO;

public interface PurchaseOrderItemService {

    PurchaseOrderItemDTO create(PurchaseOrderItemDTO purchaseOrderItemDTO);
    List<PurchaseOrderItemDTO> getAll();
    PurchaseOrderItemDTO getById(int id);
    PurchaseOrderItemDTO update(int id, PurchaseOrderItemDTO purchaseOrderItemDTO);
    void delete(int id);
}
