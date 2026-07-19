package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.SupplierReturnDTO;
import com.dmsc.back_end.entity.Grn;
import com.dmsc.back_end.entity.Item;
import com.dmsc.back_end.entity.SupplierReturn;
import com.dmsc.back_end.repository.GrnRepository;
import com.dmsc.back_end.repository.ItemRepository;
import com.dmsc.back_end.repository.SupplierReturnRepository;
import com.dmsc.back_end.service.SupplierReturnService;

import jakarta.transaction.Transactional;

@Service
public class SupplierReturnServiceImpl implements SupplierReturnService {

    @Autowired
    private SupplierReturnRepository supplierReturnRepository;

    @Autowired
    private GrnRepository grnRepository;

    @Autowired
    private ItemRepository itemRepository;


    //===================Create=================================

        @Override
    @Transactional
    public SupplierReturnDTO create(SupplierReturnDTO dto) {

        Grn grn = grnRepository.findById(dto.getGrnId())
                .orElseThrow(() -> new RuntimeException("GRN not found"));

        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (dto.getQty() <= 0) {
            throw new RuntimeException("Return quantity must be greater than zero.");
        }

        // Check stock
        if (item.getQuantity() < dto.getQty()) {
            throw new RuntimeException("Insufficient stock.");
        }

        // Reduce stock
        item.setQuantity(item.getQuantity() - dto.getQty());

        itemRepository.save(item);

        SupplierReturn supplierReturn = new SupplierReturn();

        supplierReturn.setReturnDate(dto.getReturnDate());
        supplierReturn.setReturnReason(dto.getReturnReason());
        supplierReturn.setQty(dto.getQty());

        supplierReturn.setActive(true);

        supplierReturn.setEnteredBy(dto.getEnteredBy());
        supplierReturn.setEnteredDate(LocalDate.now());

        supplierReturn.setGrn(grn);
        supplierReturn.setItem(item);

        SupplierReturn saved = supplierReturnRepository.save(supplierReturn);

        return toDTO(saved);
    }

    // =====================Dto  mapper =========================

        private SupplierReturnDTO toDTO(SupplierReturn supplierReturn) {

        SupplierReturnDTO dto = new SupplierReturnDTO();

        dto.setSupplierReturnId(supplierReturn.getSupplierReturnId());

        dto.setReturnDate(supplierReturn.getReturnDate());

        dto.setReturnReason(supplierReturn.getReturnReason());

        dto.setQty(supplierReturn.getQty());

        dto.setIsActive(supplierReturn.isActive());

        dto.setEnteredBy(supplierReturn.getEnteredBy());

        dto.setEnteredDate(supplierReturn.getEnteredDate());

        dto.setUpdateBy(supplierReturn.getUpdateBy());

        dto.setUpdateDate(supplierReturn.getUpdateDate());

        if (supplierReturn.getGrn() != null) {

            dto.setGrnId(supplierReturn.getGrn().getGrnId());

        }

        if (supplierReturn.getItem() != null) {

            dto.setItemId(supplierReturn.getItem().getItemId());

            dto.setItemName(supplierReturn.getItem().getItemName());

        }

        return dto;
    }

    //==================Update==============================
    @Override
    @Transactional
    public SupplierReturnDTO update(int id, SupplierReturnDTO dto) {

        SupplierReturn supplierReturn = supplierReturnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier Return not found"));

        Grn grn = grnRepository.findById(dto.getGrnId())
                .orElseThrow(() -> new RuntimeException("GRN not found"));

        Item oldItem = supplierReturn.getItem();

        // Restore old stock
        oldItem.setQuantity(oldItem.getQuantity() + supplierReturn.getQty());
        itemRepository.save(oldItem);

        Item newItem = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (dto.getQty() <= 0) {
            throw new RuntimeException("Return quantity must be greater than zero.");
        }

        if (newItem.getQuantity() < dto.getQty()) {
            throw new RuntimeException("Insufficient stock.");
        }

        // Reduce new stock
        newItem.setQuantity(newItem.getQuantity() - dto.getQty());
        itemRepository.save(newItem);

        supplierReturn.setReturnDate(dto.getReturnDate());
        supplierReturn.setReturnReason(dto.getReturnReason());
        supplierReturn.setQty(dto.getQty());

        supplierReturn.setUpdateBy(dto.getUpdateBy());
        supplierReturn.setUpdateDate(LocalDate.now());

        supplierReturn.setGrn(grn);
        supplierReturn.setItem(newItem);

        SupplierReturn updated = supplierReturnRepository.save(supplierReturn);

        return toDTO(updated);
    }
    
    //=====================Delete=========================

    @Override
    @Transactional
    public void delete(int id) {

        SupplierReturn supplierReturn = supplierReturnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier Return not found"));

        Item item = supplierReturn.getItem();

        // Restore stock
        item.setQuantity(item.getQuantity() + supplierReturn.getQty());

        itemRepository.save(item);

        supplierReturn.setActive(false);

        supplierReturnRepository.save(supplierReturn);
    }

    // ===================Get by Id=========================

    @Override
    public SupplierReturnDTO getById(int id) {

        SupplierReturn supplierReturn = supplierReturnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier Return not found"));

        return toDTO(supplierReturn);
    }

    // ==================Get All=========================

    @Override
    public List<SupplierReturnDTO> getAll() {

        return supplierReturnRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ==================Search by GRN Id================

    @Override
    public List<SupplierReturnDTO> searchByGrnId(int grnId) {

        return supplierReturnRepository.findByGrn_GrnId(grnId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ==================search by Item Id================

    @Override
    public List<SupplierReturnDTO> searchByItemName(String itemName) {

        return supplierReturnRepository.findByItem_ItemNameContainingIgnoreCase(itemName)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ==================search by Return Date================

    @Override
    public List<SupplierReturnDTO> searchByReturnDate(LocalDate returnDate) {

        return supplierReturnRepository.findByReturnDate(returnDate)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}