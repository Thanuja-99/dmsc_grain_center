package com.dmsc.back_end.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.GrnItemDTO;
import com.dmsc.back_end.entity.Grn;
import com.dmsc.back_end.entity.GrnItem;
import com.dmsc.back_end.entity.Item;
import com.dmsc.back_end.repository.GrnItemRepository;
import com.dmsc.back_end.repository.GrnRepository;
import com.dmsc.back_end.repository.ItemRepository;
import com.dmsc.back_end.service.GrnItemService;

@Service
public class GrnItemServiceImpl implements GrnItemService {

    @Autowired
    private GrnItemRepository grnItemRepository;

    @Autowired
    private GrnRepository grnRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public GrnItemDTO createGrnItem(GrnItemDTO dto) {

        Grn grn = grnRepository.findById(dto.getGrnId())
                .orElseThrow(() -> new RuntimeException("GRN not found"));

        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        GrnItem entity = new GrnItem();

        entity.setQtyRecived(dto.getQtyRecived());
        entity.setActive(dto.getIsActive() != null ? dto.getIsActive() : true);
        entity.setEnteredBy(dto.getEnteredBy());
        entity.setEnteredDate(dto.getEnteredDate());
        entity.setUpdateBy(dto.getUpdateBy());
        entity.setUpdateDate(dto.getUpdateDate());

        entity.setGrn(grn);
        entity.setItem(item);

        GrnItem saved = grnItemRepository.save(entity);

        return toDTO(saved);
    }

    @Override
    public List<GrnItemDTO> getAllGrnItems() {

        return grnItemRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GrnItemDTO getGrnItemById(int id) {

        GrnItem entity = grnItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GRN Item not found"));

        return toDTO(entity);
    }

    @Override
    public List<GrnItemDTO> searchGrnItems(int id) {

        return grnItemRepository.findAll()
                .stream()
                .filter(g -> g.getGrnItemId() == id)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<GrnItemDTO> searchGrnItems(String itemName) {

        return grnItemRepository
                .findByItem_ItemNameContainingIgnoreCase(itemName)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GrnItemDTO updateGrnItem(int id, GrnItemDTO dto) {

        GrnItem entity = grnItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GRN Item not found"));

        Grn grn = grnRepository.findById(dto.getGrnId())
                .orElseThrow(() -> new RuntimeException("GRN not found"));

        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        entity.setQtyRecived(dto.getQtyRecived());
        entity.setActive(dto.getIsActive());
        entity.setEnteredBy(dto.getEnteredBy());
        entity.setEnteredDate(dto.getEnteredDate());
        entity.setUpdateBy(dto.getUpdateBy());
        entity.setUpdateDate(dto.getUpdateDate());

        entity.setGrn(grn);
        entity.setItem(item);

        GrnItem updated = grnItemRepository.save(entity);

        return toDTO(updated);
    }

    @Override
    public void deleteGrnItem(int id) {

        GrnItem entity = grnItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GRN Item not found"));

        entity.setActive(false);

        grnItemRepository.save(entity);
    }

    private GrnItemDTO toDTO(GrnItem entity) {

        GrnItemDTO dto = new GrnItemDTO();

        dto.setGrnItemId(entity.getGrnItemId());
        dto.setQtyRecived(entity.getQtyRecived());
        dto.setIsActive(entity.isActive());
        dto.setEnteredBy(entity.getEnteredBy());
        dto.setEnteredDate(entity.getEnteredDate());
        dto.setUpdateBy(entity.getUpdateBy());
        dto.setUpdateDate(entity.getUpdateDate());

        dto.setGrnId(entity.getGrn().getGrnId());

        dto.setItemId(entity.getItem().getItemId());
        dto.setItemName(entity.getItem().getItemName());

        return dto;
    }

}