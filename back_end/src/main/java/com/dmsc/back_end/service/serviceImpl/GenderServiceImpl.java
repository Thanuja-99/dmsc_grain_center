package com.dmsc.back_end.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.entity.Gender;
import com.dmsc.back_end.repository.GenderRepository;
import com.dmsc.back_end.service.GenderService;

@Service
public class GenderServiceImpl implements GenderService{

    @Autowired
    private GenderRepository genderRepository;

    @Override
    public Gender createGender(Gender gender) {
        return genderRepository.save(gender);
    }

    @Override
    public List<Gender> getAllGenders() {
        return genderRepository.findAll();
    }

    @Override
    public Gender getGenderById(int id) {
        return genderRepository.findById(id).orElse(null);
    }

    @Override
    public Gender updateGender(int id, Gender gender) {

        Gender existingGender = genderRepository.findById(id).orElse(null);

        if (existingGender!= null) {
            existingGender.setGenderName(gender.getGenderName());
            return genderRepository.save(existingGender);
        }

        return null;
    }

    @Override
    public void deleteGender(int id) {
        genderRepository.deleteById(id);
    }
}
