package com.dmsc.back_end.service;

import java.util.List;
import com.dmsc.back_end.dto.StoreDTO;

public interface StoreService {

    StoreDTO createStore(StoreDTO storeDTO);

    List<StoreDTO> getAllStores();

    StoreDTO getStoreById(int id);

    List<StoreDTO> searchStore(String name);

    StoreDTO updateStore(int id, StoreDTO storeDTO);

    void deleteStore(int id);
}