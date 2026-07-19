package com.dmsc.back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.Grn;

public interface GrnRepository extends JpaRepository<Grn, Integer> {

    List<Grn> findByGrnNoteContainingIgnoreCase(String grnNote);
}
