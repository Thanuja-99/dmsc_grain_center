package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.BillingDTO;

public interface BillingService {

    BillingDTO createBilling(BillingDTO billingDTO);

    List<BillingDTO> getAllBillings();

    BillingDTO getBillingById(int id);

    List<BillingDTO> searchBillings(int id);

    List<BillingDTO> searchBillings(String customerName);

    BillingDTO updateBilling(int id, BillingDTO billingDTO);

    void deleteBilling(int id);

}