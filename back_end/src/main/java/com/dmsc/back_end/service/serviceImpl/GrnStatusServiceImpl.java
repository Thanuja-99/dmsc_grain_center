package com.dmsc.back_end.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.GrnStatusDTO;
import com.dmsc.back_end.entity.GrnStatus;
import com.dmsc.back_end.repository.GrnStatusRepository;
import com.dmsc.back_end.service.GrnStatusService;

@Service
public class GrnStatusServiceImpl implements GrnStatusService {

    @Autowired
    private GrnStatusRepository grnStatusRepository;

    @Override
    public GrnStatusDTO createGrnStatus(GrnStatusDTO grnStatusDTO) {

        GrnStatus grnStatus = new GrnStatus();

        grnStatus.setGrnStatusName(grnStatusDTO.getGrnStatusName());
        grnStatus.setActive(grnStatusDTO.getIsActive() != null ? grnStatusDTO.getIsActive() : true);
        grnStatus.setEnteredBy(grnStatusDTO.getEnteredBy());
        grnStatus.setEnteredDate(grnStatusDTO.getEnteredDate());
        grnStatus.setUpdateBy(grnStatusDTO.getUpdateBy());
        grnStatus.setUpdateDate(grnStatusDTO.getUpdateDate());

        GrnStatus saved = grnStatusRepository.save(grnStatus);

        return toDTO(saved);
    }

    @Override
    public List<GrnStatusDTO> getAllGrnStatuses() {

        return grnStatusRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GrnStatusDTO getGrnStatusById(int id) {

        GrnStatus grnStatus = grnStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GRN Status not found"));

        return toDTO(grnStatus);
    }

    @Override
    public List<GrnStatusDTO> searchGrnStatuses(int id) {

        return grnStatusRepository.findAll()
                .stream()
                .filter(status -> status.getGrnStatusId() == id)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<GrnStatusDTO> searchGrnStatuses(String name) {

        return grnStatusRepository.findByGrnStatusNameContainingIgnoreCase(name)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GrnStatusDTO updateGrnStatus(int id, GrnStatusDTO grnStatusDTO) {

        GrnStatus grnStatus = grnStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GRN Status not found"));

        grnStatus.setGrnStatusName(grnStatusDTO.getGrnStatusName());
        grnStatus.setActive(grnStatusDTO.getIsActive());
        grnStatus.setEnteredBy(grnStatusDTO.getEnteredBy());
        grnStatus.setEnteredDate(grnStatusDTO.getEnteredDate());
        grnStatus.setUpdateBy(grnStatusDTO.getUpdateBy());
        grnStatus.setUpdateDate(grnStatusDTO.getUpdateDate());

        GrnStatus updated = grnStatusRepository.save(grnStatus);

        return toDTO(updated);
    }

   @Override
    public void deleteGrnStatus(int id) {

        GrnStatus grnStatus = grnStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GRN Status not found"));

        grnStatus.setActive(false);

        grnStatusRepository.save(grnStatus);
    }


     // ================= ENTITY -> DTO =================
    private GrnStatusDTO toDTO(GrnStatus grnStatus) {

        GrnStatusDTO dto = new GrnStatusDTO();

        dto.setGrnStatusId(grnStatus.getGrnStatusId());
        dto.setGrnStatusName(grnStatus.getGrnStatusName());
        dto.setIsActive(grnStatus.isActive());

        dto.setEnteredBy(grnStatus.getEnteredBy());
        dto.setEnteredDate(grnStatus.getEnteredDate());

        dto.setUpdateBy(grnStatus.getUpdateBy());
        dto.setUpdateDate(grnStatus.getUpdateDate());

        return dto;
    }
}