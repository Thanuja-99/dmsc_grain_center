package com.dmsc.back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dmsc.back_end.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    List<Store> findByStoreNameContainingIgnoreCase(String name);
}