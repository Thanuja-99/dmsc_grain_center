package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.PurchaseOrderStatusDTO;
import com.dmsc.back_end.entity.PurchaseOrderStatus;
import com.dmsc.back_end.repository.PurchaseOrderStatusRepository;
import com.dmsc.back_end.service.PurchaseOrderStatusService;

@Service
public class PurchaseOrderStatusServiceImpl implements PurchaseOrderStatusService {

    @Autowired
    private PurchaseOrderStatusRepository purchaseOrderStatusRepository;

    // ================= CREATE =================
    @Override
    public PurchaseOrderStatusDTO create(PurchaseOrderStatusDTO purchaseOrderStatusDTO) {

        PurchaseOrderStatus purchaseOrderStatus = new PurchaseOrderStatus();

        purchaseOrderStatus.setPurchaseOrderStatus(purchaseOrderStatusDTO.getPurchaseOrderStatus());
        purchaseOrderStatus.setActive(true);
        purchaseOrderStatus.setEnteredBy(purchaseOrderStatusDTO.getEnteredBy());
        purchaseOrderStatus.setEnteredDate(LocalDate.now());

        PurchaseOrderStatus saved = purchaseOrderStatusRepository.save(purchaseOrderStatus);

        return toDTO(saved);
    }

    // ================= GET ALL =================
    @Override
    public List<PurchaseOrderStatusDTO> getAll() {
        return purchaseOrderStatusRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= GET BY ID =================
    @Override
    public PurchaseOrderStatusDTO getById(int id) {
        PurchaseOrderStatus status = purchaseOrderStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order Status not found"));

        return toDTO(status);
    }

    // ================= UPDATE =================
    @Override
    public PurchaseOrderStatusDTO update(int id, PurchaseOrderStatusDTO purchaseOrderStatusDTO) {

        PurchaseOrderStatus purchaseOrderStatus = purchaseOrderStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order Status not found"));

        purchaseOrderStatus.setPurchaseOrderStatus(purchaseOrderStatusDTO.getPurchaseOrderStatus());
        purchaseOrderStatus.setActive(purchaseOrderStatusDTO.isActive());
        purchaseOrderStatus.setUpdateBy(purchaseOrderStatusDTO.getUpdateBy());
        purchaseOrderStatus.setUpdateDate(LocalDate.now());

        PurchaseOrderStatus updated = purchaseOrderStatusRepository.save(purchaseOrderStatus);

        return toDTO(updated);
    }

    // ================= DELETE (SOFT DELETE) =================
    @Override
    public void delete(int id) {

        PurchaseOrderStatus purchaseOrderStatus = purchaseOrderStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order Status not found"));

        purchaseOrderStatus.setActive(false);
        purchaseOrderStatusRepository.save(purchaseOrderStatus);
    }

    // ================= ENTITY → DTO =================
    private PurchaseOrderStatusDTO toDTO(PurchaseOrderStatus purchaseOrderStatus) {

        PurchaseOrderStatusDTO purchaseOrderStatusDTO = new PurchaseOrderStatusDTO();

        purchaseOrderStatusDTO.setPurchaseOrderId(purchaseOrderStatus.getPurchaseOrderId());
        purchaseOrderStatusDTO.setPurchaseOrderStatus(purchaseOrderStatus.getPurchaseOrderStatus());
        purchaseOrderStatusDTO.setActive(purchaseOrderStatus.isActive());
        purchaseOrderStatusDTO.setEnteredBy(purchaseOrderStatus.getEnteredBy());
        purchaseOrderStatusDTO.setEnteredDate(purchaseOrderStatus.getEnteredDate());
        purchaseOrderStatusDTO.setUpdateBy(purchaseOrderStatus.getUpdateBy());
        purchaseOrderStatusDTO.setUpdateDate(purchaseOrderStatus.getUpdateDate());

        return purchaseOrderStatusDTO;
    }
}