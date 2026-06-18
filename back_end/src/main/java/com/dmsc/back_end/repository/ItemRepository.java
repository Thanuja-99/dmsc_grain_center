package com.dmsc.back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    
    List<Item> findByItemNameContainingIgnoreCase(String name);
}
