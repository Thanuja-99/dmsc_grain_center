package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.BillingDTO;
import com.dmsc.back_end.entity.BillStatus;
import com.dmsc.back_end.entity.Billing;
import com.dmsc.back_end.entity.Customer;
import com.dmsc.back_end.entity.CustomerOrder;
import com.dmsc.back_end.entity.CustomerOrderItem;
import com.dmsc.back_end.repository.BillStatusRepository;
import com.dmsc.back_end.repository.BillingRepository;
import com.dmsc.back_end.repository.CustomerOrderRepository;
import com.dmsc.back_end.service.BillingService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private BillStatusRepository billStatusRepository;

    // =====================CREATE BILL========================================
  
    @Override
    public BillingDTO createBilling(BillingDTO dto) {

        // Check Customer Order

        CustomerOrder customerOrder = customerOrderRepository.findById(dto.getCustomerOrderId())
                .orElseThrow(() -> new RuntimeException("Customer Order not found"));

        // Customer comes from Customer Order

        Customer customer = customerOrder.getCustomer();

        // Bill Status

        BillStatus billStatus = billStatusRepository.findById(dto.getBillStatusId())
                .orElseThrow(() -> new RuntimeException("Bill Status not found"));

        // Calculate Total Amount

        double totalAmount = 0;

        if (customerOrder.getItems() != null) {

            for (CustomerOrderItem item : customerOrder.getItems()) {

                totalAmount += item.getQty() * item.getUnitPrice();

            }
        }

        // Create Billing

        Billing billing = new Billing();

        billing.setBillDate(dto.getBillDate());
        billing.setTotalAmount(totalAmount);

        billing.setActive(true);

        billing.setEnteredBy(dto.getEnteredBy());
        billing.setEnteredDate(LocalDate.now());

        billing.setCustomer(customer);
        billing.setCustomerOrder(customerOrder);
        billing.setBillStatus(billStatus);

        Billing savedBilling = billingRepository.save(billing);

        return toDTO(savedBilling);

    }

    // ===================ENTITY -> DTO===================================
   

    private BillingDTO toDTO(Billing billing) {

        BillingDTO dto = new BillingDTO();

        dto.setBillId(billing.getBillId());

        // Runtime Bill Number
        dto.setBillNo("INV-" + String.format("%06d", billing.getBillId()));

        dto.setBillDate(billing.getBillDate());

        dto.setTotalAmount(billing.getTotalAmount());

        dto.setIsActive(billing.isActive());

        dto.setEnteredBy(billing.getEnteredBy());
        dto.setEnteredDate(billing.getEnteredDate());

        dto.setUpdateBy(billing.getUpdateBy());
        dto.setUpdateDate(billing.getUpdateDate());

        if (billing.getCustomerOrder() != null) {

            dto.setCustomerOrderId(
                    billing.getCustomerOrder().getCustomerOrderId());

        }

        if (billing.getCustomer() != null) {

            dto.setCustomerId(
                    billing.getCustomer().getCustomerId());

            dto.setCustomerName(
                    billing.getCustomer().getCallingName());

        }

        if (billing.getBillStatus() != null) {

            dto.setBillStatusId(
                    billing.getBillStatus().getBillStatusId());

            dto.setBillStatusName(
                    billing.getBillStatus().getBillStatusName());

        }

        return dto;

    }

    // ================GET ALL BILLINGS=================================

    @Override
    public List<BillingDTO> getAllBillings() {

        return billingRepository.findAll()
                .stream()
                .filter(Billing::isActive) // Return only active bills
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ==============GET BILL BY ID=============================

    @Override
    public BillingDTO getBillingById(int id) {

        Billing billing = billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Billing not found"));

        return toDTO(billing);
    }

    // ===================SEARCH BY BILL ID===========================

    @Override
    public List<BillingDTO> searchBillings(int id) {

        return billingRepository.findAll()
                .stream()
                .filter(b -> b.getBillId() == id)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ====================SEARCH BY CUSTOMER NAME======================

    @Override
    public List<BillingDTO> searchBillings(String customerName) {

        return billingRepository
                .findByCustomer_CallingNameContainingIgnoreCase(customerName)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ======================UPDATE BILL==============================
    
    @Override
    public BillingDTO updateBilling(int id, BillingDTO dto) {

        Billing billing = billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Billing not found"));

        // Business Rule:
        // Customer Order cannot be changed after billing.
        // Only Bill Status and update information can be modified.

        if (dto.getBillStatusId() != null) {

            BillStatus status = billStatusRepository.findById(dto.getBillStatusId())
                    .orElseThrow(() -> new RuntimeException("Bill Status not found"));

            billing.setBillStatus(status);
        }

        if (dto.getBillDate() != null) {
            billing.setBillDate(dto.getBillDate());
        }

        billing.setUpdateBy(dto.getUpdateBy());
        billing.setUpdateDate(LocalDate.now());

        Billing updated = billingRepository.save(billing);

        return toDTO(updated);
    }

    // ==================SOFT DELETE BILL==============================
   
    @Override
    public void deleteBilling(int id) {

        Billing billing = billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Billing not found"));

        // Business Rule:
        // Physical deletion is not allowed.
        // Mark bill as inactive.

        billing.setActive(false);

        billingRepository.save(billing);
    }

}
