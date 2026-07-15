package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.PurchaseOrderStatusDTO;

public interface PurchaseOrderStatusService {

    PurchaseOrderStatusDTO create(PurchaseOrderStatusDTO purchaseOrderStatusDTO);
    List<PurchaseOrderStatusDTO> getAll();
    PurchaseOrderStatusDTO getById(int id);
    PurchaseOrderStatusDTO update(int id, PurchaseOrderStatusDTO purchaseOrderStatusDTO);
    void delete(int id);
}
