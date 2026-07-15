package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.PurchaseOrderDTO;

public interface PurchaseOrderService {
    
    PurchaseOrderDTO create(PurchaseOrderDTO purchaseOrderDTO);
    List<PurchaseOrderDTO> getAll();
    PurchaseOrderDTO getById(int id);
    PurchaseOrderDTO update(int id, PurchaseOrderDTO purchaseOrderDTO);
    void delete(int id);
}
