package com.dmsc.back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.GrnStatus;

public interface GrnStatusRepository extends JpaRepository<GrnStatus, Integer> {
    List<GrnStatus> findByGrnStatusNameContainingIgnoreCase(String name);
}
