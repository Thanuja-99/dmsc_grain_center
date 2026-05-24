package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.entity.Gender;

public interface GenderService {
    Gender createGender(Gender gender);

    List<Gender> getAllGenders();

    Gender getGenderById(int id);

    Gender updateGender(int id, Gender gender);

    void deleteGender(int id);
}
