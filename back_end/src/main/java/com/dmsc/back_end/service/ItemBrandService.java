package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.ItemBrandDTO;

public interface ItemBrandService {
    
    ItemBrandDTO createItemBrand(ItemBrandDTO itemBrandDTO);
    List<ItemBrandDTO> getAllItemBrands();
    ItemBrandDTO getItemBrandById(int id);
    List<ItemBrandDTO> searchItemBrands(int id);
    List<ItemBrandDTO> searchItemBrands(String name);
    ItemBrandDTO updateItemBrand(int id, ItemBrandDTO itemBrandDTO);
    void deleteItemBrand(int id);
}
