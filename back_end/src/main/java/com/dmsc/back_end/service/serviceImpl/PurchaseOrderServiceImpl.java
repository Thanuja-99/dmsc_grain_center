package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.PurchaseOrderDTO;
import com.dmsc.back_end.entity.PurchaseOrder;
import com.dmsc.back_end.entity.PurchaseOrderStatus;
import com.dmsc.back_end.entity.Store;
import com.dmsc.back_end.entity.Supplier;
import com.dmsc.back_end.repository.PurchaseOrderRepository;
import com.dmsc.back_end.repository.PurchaseOrderStatusRepository;
import com.dmsc.back_end.repository.StoreRepository;
import com.dmsc.back_end.repository.SupplierRepository;
import com.dmsc.back_end.service.PurchaseOrderService;

import jakarta.transaction.Transactional;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private PurchaseOrderStatusRepository statusRepository;

    // ================= GET BY ID =================
    @Override
    public PurchaseOrderDTO getById(int id) {

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found"));

        return toDTO(purchaseOrder);
    }

    // ================= GET ALL =================
    @Override
    public List<PurchaseOrderDTO> getAll() {

        return purchaseOrderRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= CREATE =================
    @Override
    @Transactional
    public PurchaseOrderDTO create(PurchaseOrderDTO purchaseOrderDTO) {

        PurchaseOrder purchaseOrder = new PurchaseOrder();

        purchaseOrder.setOrderDate(purchaseOrderDTO.getOrderDate() != null ? purchaseOrderDTO.getOrderDate() : LocalDate.now());
        purchaseOrder.setPurchaseOrderNote(purchaseOrderDTO.getPurchaseOrderNote());

        purchaseOrder.setActive(true);

        purchaseOrder.setEnteredBy(purchaseOrderDTO.getEnteredBy());
        purchaseOrder.setEnteredDate(LocalDate.now());

        // ================= RELATIONS =================
        if (purchaseOrderDTO.getSupplierId() != null) {
            Supplier supplier = supplierRepository.findById(purchaseOrderDTO.getSupplierId())
                    .orElseThrow(() -> new RuntimeException("Supplier not found"));
            purchaseOrder.setSupplier(supplier);
        }

        if (purchaseOrderDTO.getStoreId() != null) {
            Store store = storeRepository.findById(purchaseOrderDTO.getStoreId())
                    .orElseThrow(() -> new RuntimeException("Store not found"));
            purchaseOrder.setStore(store);
        }

        if (purchaseOrderDTO.getStatusId() != null) {
            PurchaseOrderStatus status = statusRepository.findById(purchaseOrderDTO.getStatusId())
                    .orElseThrow(() -> new RuntimeException("Status not found"));
            purchaseOrder.setStatus(status);
        }

        PurchaseOrder saved = purchaseOrderRepository.save(purchaseOrder);

        return toDTO(saved);
    }

    // ================= UPDATE =================
    @Override
    @Transactional
    public PurchaseOrderDTO update(int id, PurchaseOrderDTO purchaseOrderDTO) {

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found"));

        purchaseOrder.setOrderDate(purchaseOrderDTO.getOrderDate());
        purchaseOrder.setPurchaseOrderNote(purchaseOrderDTO.getPurchaseOrderNote());

        purchaseOrder.setUpdateBy(purchaseOrderDTO.getUpdateBy());
        purchaseOrder.setUpdateDate(LocalDate.now());

        if (purchaseOrderDTO.getSupplierId() != null) {
            Supplier supplier = supplierRepository.findById(purchaseOrderDTO.getSupplierId())
                    .orElseThrow(() -> new RuntimeException("Supplier not found"));
            purchaseOrder.setSupplier(supplier);
        }

        if (purchaseOrderDTO.getStoreId() != null) {
            Store store = storeRepository.findById(purchaseOrderDTO.getStoreId())
                    .orElseThrow(() -> new RuntimeException("Store not found"));
            purchaseOrder.setStore(store);
        }

        if (purchaseOrderDTO.getStatusId() != null) {
            PurchaseOrderStatus status = statusRepository.findById(purchaseOrderDTO.getStatusId())
                    .orElseThrow(() -> new RuntimeException("Status not found"));
            purchaseOrder.setStatus(status);
        }

        PurchaseOrder updated = purchaseOrderRepository.save(purchaseOrder);

        return toDTO(updated);
    }

    // ================= DELETE (SOFT DELETE) =================
    @Override
    public void delete(int id) {

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found"));

        purchaseOrder.setActive(false);

        purchaseOrderRepository.save(purchaseOrder);
    }

    // ================= DTO MAPPER =================
    private PurchaseOrderDTO toDTO(PurchaseOrder purchaseOrder) {

        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();

        purchaseOrderDTO.setPurchaseOrderId(purchaseOrder.getPurchaseOrderId());
        purchaseOrderDTO.setOrderDate(purchaseOrder.getOrderDate());
        purchaseOrderDTO.setPurchaseOrderNote(purchaseOrder.getPurchaseOrderNote());

        purchaseOrderDTO.setActive(purchaseOrder.isActive());

        purchaseOrderDTO.setEnteredBy(purchaseOrder.getEnteredBy());
        purchaseOrderDTO.setEnteredDate(purchaseOrder.getEnteredDate());

        purchaseOrderDTO.setUpdateBy(purchaseOrder.getUpdateBy());
        purchaseOrderDTO.setUpdateDate(purchaseOrder.getUpdateDate());

        if (purchaseOrder.getSupplier() != null) {
            purchaseOrderDTO.setSupplierId(purchaseOrder.getSupplier().getSupplierId());
        }

        if (purchaseOrder.getStore() != null) {
            purchaseOrderDTO.setStoreId(purchaseOrder.getStore().getStoreId());
        }

        if (purchaseOrder.getStatus() != null) {
            purchaseOrderDTO.setStatusId(purchaseOrder.getStatus().getPurchaseOrderId());
        }

        return purchaseOrderDTO;
    }
}