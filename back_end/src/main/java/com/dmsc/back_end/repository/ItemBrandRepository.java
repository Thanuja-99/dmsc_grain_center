package com.dmsc.back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.ItemBrand;

public interface ItemBrandRepository extends JpaRepository<ItemBrand, Integer> {
    List<ItemBrand> findByItemBrandNameContainingIgnoreCase(String name);
}
