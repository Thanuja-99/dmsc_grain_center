package com.dmsc.back_end.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.BillStatusDTO;
import com.dmsc.back_end.entity.BillStatus;
import com.dmsc.back_end.repository.BillStatusRepository;
import com.dmsc.back_end.service.BillStatusService;

@Service
public class BillStatusServiceImpl implements BillStatusService {

    @Autowired
    private BillStatusRepository billStatusRepository;

    // ================= CREATE =================
    @Override
    public BillStatusDTO createBillStatus(BillStatusDTO dto) {

        BillStatus billStatus = new BillStatus();

        billStatus.setBillStatusName(dto.getBillStatusName());
        billStatus.setActive(dto.getIsActive() != null ? dto.getIsActive() : true);
        billStatus.setEnteredBy(dto.getEnteredBy());
        billStatus.setEnteredDate(dto.getEnteredDate());
        billStatus.setUpdateBy(dto.getUpdateBy());
        billStatus.setUpdateDate(dto.getUpdateDate());

        BillStatus saved = billStatusRepository.save(billStatus);

        return toDTO(saved);
    }

    // ================= GET ALL =================
    @Override
    public List<BillStatusDTO> getAllBillStatuses() {

        return billStatusRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= GET BY ID =================
    @Override
    public BillStatusDTO getBillStatusById(int id) {

        BillStatus billStatus = billStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill Status not found"));

        return toDTO(billStatus);
    }

    // ================= SEARCH BY ID =================
    @Override
    public List<BillStatusDTO> searchBillStatuses(int id) {

        return billStatusRepository.findAll()
                .stream()
                .filter(status -> status.getBillStatusId() == id)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= SEARCH BY NAME =================
    @Override
    public List<BillStatusDTO> searchBillStatuses(String name) {

        return billStatusRepository
                .findByBillStatusNameContainingIgnoreCase(name)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= UPDATE =================
    @Override
    public BillStatusDTO updateBillStatus(int id, BillStatusDTO dto) {

        BillStatus billStatus = billStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill Status not found"));

        billStatus.setBillStatusName(dto.getBillStatusName());

        if (dto.getIsActive() != null) {
            billStatus.setActive(dto.getIsActive());
        }

        billStatus.setUpdateBy(dto.getUpdateBy());
        billStatus.setUpdateDate(dto.getUpdateDate());

        BillStatus updated = billStatusRepository.save(billStatus);

        return toDTO(updated);
    }

    // ================= SOFT DELETE =================
    @Override
    public void deleteBillStatus(int id) {

        BillStatus billStatus = billStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill Status not found"));

        billStatus.setActive(false);

        billStatusRepository.save(billStatus);
    }

    // ================= ENTITY -> DTO =================
    private BillStatusDTO toDTO(BillStatus billStatus) {

        BillStatusDTO dto = new BillStatusDTO();

        dto.setBillStatusId(billStatus.getBillStatusId());
        dto.setBillStatusName(billStatus.getBillStatusName());

        dto.setIsActive(billStatus.isActive());

        dto.setEnteredBy(billStatus.getEnteredBy());
        dto.setEnteredDate(billStatus.getEnteredDate());

        dto.setUpdateBy(billStatus.getUpdateBy());
        dto.setUpdateDate(billStatus.getUpdateDate());

        return dto;
    }

}