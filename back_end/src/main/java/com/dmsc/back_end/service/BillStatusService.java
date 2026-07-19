package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.BillStatusDTO;

public interface BillStatusService {

    BillStatusDTO createBillStatus(BillStatusDTO billStatusDTO);

    List<BillStatusDTO> getAllBillStatuses();

    BillStatusDTO getBillStatusById(int id);

    List<BillStatusDTO> searchBillStatuses(int id);

    List<BillStatusDTO> searchBillStatuses(String name);

    BillStatusDTO updateBillStatus(int id, BillStatusDTO billStatusDTO);

    void deleteBillStatus(int id);

}