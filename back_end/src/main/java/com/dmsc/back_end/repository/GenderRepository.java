package com.dmsc.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.Gender;


    public interface GenderRepository extends JpaRepository<Gender, Integer> {}

