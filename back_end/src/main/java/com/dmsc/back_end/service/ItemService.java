package com.dmsc.back_end.service;

import java.util.List;


import com.dmsc.back_end.dto.ItemDTO;

public interface ItemService {
    ItemDTO createItem(ItemDTO itemDTO);
    List<ItemDTO> getAllItems();
    ItemDTO getItemById(int id);
    List<ItemDTO> searchItems(int id);
    List<ItemDTO> searchItems(String name);
    ItemDTO updateItem(int id, ItemDTO itemDTO);
    void deleteItem(int id);
}
