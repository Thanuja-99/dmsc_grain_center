package com.dmsc.back_end.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.GenderDTO;
import com.dmsc.back_end.entity.Gender;
import com.dmsc.back_end.repository.GenderRepository;
import com.dmsc.back_end.service.GenderService;

@Service
public class GenderServiceImpl implements GenderService{

    @Autowired
    GenderRepository genderRepository;

    @Override
    public List<GenderDTO> getAllGenders() {
        return genderRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GenderDTO getGenderById(int id) {
        Gender gender = genderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gender not found"));
        return toDTO(gender);
    }

    @Override
    public GenderDTO createGender(GenderDTO dto) {
        Gender gender = new Gender();
        gender.setGenderName(dto.getGenderName());
        Gender saved = genderRepository.save(gender);
        return toDTO(saved);
    }

    @Override
    public GenderDTO updateGender(int id, GenderDTO dto) {
        Gender gender = genderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gender not found"));
        gender.setGenderName(dto.getGenderName());
        Gender updated = genderRepository.save(gender);
        return toDTO(updated);
    }

    @Override
    public void deleteGender(int id) {
        genderRepository.deleteById(id);
    }

    private GenderDTO toDTO(Gender gender) {
        return new GenderDTO(gender.getGenderId(), gender.getGenderName());
    }
}
