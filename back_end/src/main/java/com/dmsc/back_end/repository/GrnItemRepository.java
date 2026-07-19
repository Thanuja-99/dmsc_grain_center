package com.dmsc.back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.GrnItem;

public interface GrnItemRepository extends JpaRepository<GrnItem,Integer>{

    List<GrnItem> findByItem_ItemNameContainingIgnoreCase(String itemName);
}
