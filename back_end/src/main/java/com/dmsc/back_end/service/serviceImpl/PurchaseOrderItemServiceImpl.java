package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.PurchaseOrderItemDTO;
import com.dmsc.back_end.entity.Item;
import com.dmsc.back_end.entity.PurchaseOrder;
import com.dmsc.back_end.entity.PurchaseOrderItem;
import com.dmsc.back_end.entity.Supplier;
import com.dmsc.back_end.repository.ItemRepository;
import com.dmsc.back_end.repository.PurchaseOrderItemRepository;
import com.dmsc.back_end.repository.PurchaseOrderRepository;
import com.dmsc.back_end.repository.SupplierRepository;
import com.dmsc.back_end.service.PurchaseOrderItemService;

import jakarta.transaction.Transactional;

@Service
public class PurchaseOrderItemServiceImpl implements PurchaseOrderItemService {

    @Autowired
    private PurchaseOrderItemRepository purchaseOrderItemRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    // ================= GET BY ID =================
    @Override
    public PurchaseOrderItemDTO getById(int id) {

        PurchaseOrderItem purchaseOrderItem = purchaseOrderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order Item not found"));

        return toDTO(purchaseOrderItem);
    }

    // ================= GET ALL =================
    @Override
    public List<PurchaseOrderItemDTO> getAll() {

        return purchaseOrderItemRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= CREATE (STOCK INCREASE) =================
    @Override
    @Transactional
    public PurchaseOrderItemDTO create(PurchaseOrderItemDTO purchaseOrderItemDTO) {

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(purchaseOrderItemDTO.getPurchaseOrderId())
                .orElseThrow(() -> new RuntimeException("Purchase Order not found"));

        Item item = itemRepository.findById(purchaseOrderItemDTO.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        Supplier supplier = supplierRepository.findById(purchaseOrderItemDTO.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        PurchaseOrderItem entity = new PurchaseOrderItem();

        entity.setQuantity(purchaseOrderItemDTO.getQuantity());
        entity.setActive(true);
        entity.setEnteredBy(purchaseOrderItemDTO.getEnteredBy());
        entity.setEnteredDate(LocalDate.now());

        entity.setPurchaseOrder(purchaseOrder);
        entity.setItem(item);
        entity.setSupplier(supplier);

        PurchaseOrderItem saved = purchaseOrderItemRepository.save(entity);

        // ================= STOCK INCREASE =================
        try {
            int currentQty = item.getQuantity(); // Integer directly
            int addQty = purchaseOrderItemDTO.getQuantity();

            item.setQuantity(currentQty + addQty); // Integer direct add
            itemRepository.save(item);

        } catch (Exception e) {
            throw new RuntimeException("Item quantity format error (must be number)");
        }

        return toDTO(saved);
    }

    // ================= UPDATE =================
    @Override
    @Transactional
    public PurchaseOrderItemDTO update(int id, PurchaseOrderItemDTO purchaseOrderItemDTO) {

        PurchaseOrderItem entity = purchaseOrderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order Item not found"));

        entity.setQuantity(purchaseOrderItemDTO.getQuantity());
        entity.setUpdateBy(purchaseOrderItemDTO.getUpdateBy());
        entity.setUpdateDate(LocalDate.now());

        PurchaseOrderItem updated = purchaseOrderItemRepository.save(entity);

        return toDTO(updated);
    }

    // ================= DELETE (SOFT DELETE) =================
    @Override
    public void delete(int id) {

        PurchaseOrderItem entity = purchaseOrderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order Item not found"));

        entity.setActive(false);
        purchaseOrderItemRepository.save(entity);
    }

    // ================= DTO MAPPING =================
    private PurchaseOrderItemDTO toDTO(PurchaseOrderItem entity) {

        PurchaseOrderItemDTO dto = new PurchaseOrderItemDTO();

        dto.setPurchaseItemId(entity.getPurchaseItemId());
        dto.setQuantity(entity.getQuantity());
        dto.setActive(entity.isActive());

        dto.setEnteredBy(entity.getEnteredBy());
        dto.setEnteredDate(entity.getEnteredDate());
        dto.setUpdateBy(entity.getUpdateBy());
        dto.setUpdateDate(entity.getUpdateDate());

        if (entity.getPurchaseOrder() != null) {
            dto.setPurchaseOrderId(entity.getPurchaseOrder().getPurchaseOrderId());
        }

        if (entity.getItem() != null) {
            dto.setItemId(entity.getItem().getItemId());
        }

        if (entity.getSupplier() != null) {
            dto.setSupplierId(entity.getSupplier().getSupplierId());
        }

        return dto;
    }
}