package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.CustomerOrderStatusDTO;
import com.dmsc.back_end.entity.CustomerOrderStatus;
import com.dmsc.back_end.repository.CustomerOrderStatusRepository;
import com.dmsc.back_end.service.CustomerOrderStatusService;

import jakarta.transaction.Transactional;

@Service
public class CustomerOrderStatusServiceImpl implements CustomerOrderStatusService {

    @Autowired
    private CustomerOrderStatusRepository customerOrderStatusRepository;

    // ================= GET BY ID =================
    @Override
    public CustomerOrderStatusDTO getById(int id) {

        CustomerOrderStatus customerOrderStatus = customerOrderStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Order Status not found"));

        return toDTO(customerOrderStatus);
    }

    // ================= GET ALL =================
    @Override
    public List<CustomerOrderStatusDTO> getAll() {

        return customerOrderStatusRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= CREATE =================
    @Override
    @Transactional
    public CustomerOrderStatusDTO create(CustomerOrderStatusDTO customerOrderStatusDTO) {

        CustomerOrderStatus customerOrderStatus = new CustomerOrderStatus();

        customerOrderStatus.setStatusName(customerOrderStatusDTO.getStatusName());

        customerOrderStatus.setActive(true);

        customerOrderStatus.setEnteredBy(customerOrderStatusDTO.getEnteredBy());
        customerOrderStatus.setEnteredDate(LocalDate.now());

        customerOrderStatus.setUpdateBy(customerOrderStatusDTO.getUpdateBy());
        customerOrderStatus.setUpdateDate(LocalDate.now());

        CustomerOrderStatus saved = customerOrderStatusRepository.save(customerOrderStatus);

        return toDTO(saved);
    }

    // ================= UPDATE =================
    @Override
    @Transactional
    public CustomerOrderStatusDTO update(int id, CustomerOrderStatusDTO customerOrderStatusDTO) {

        CustomerOrderStatus customerOrderStatus = customerOrderStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Order Status not found"));

        customerOrderStatus.setStatusName(customerOrderStatusDTO.getStatusName());

        customerOrderStatus.setUpdateBy(customerOrderStatusDTO.getUpdateBy());
        customerOrderStatus.setUpdateDate(LocalDate.now());

        CustomerOrderStatus updated = customerOrderStatusRepository.save(customerOrderStatus);

        return toDTO(updated);
    }

    // ================= DELETE (SOFT DELETE) =================
    @Override
    public void delete(int id) {

        CustomerOrderStatus customerOrderStatus = customerOrderStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Order Status not found"));

        customerOrderStatus.setActive(false);

        customerOrderStatusRepository.save(customerOrderStatus);
    }

    // ================= ENTITY -> DTO =================
    private CustomerOrderStatusDTO toDTO(CustomerOrderStatus customerOrderStatus) {

        CustomerOrderStatusDTO customerOrderStatusDTO = new CustomerOrderStatusDTO();

        customerOrderStatusDTO.setStatusId(customerOrderStatus.getStatusId());
        customerOrderStatusDTO.setStatusName(customerOrderStatus.getStatusName());

        customerOrderStatusDTO.setActive(customerOrderStatus.isActive());

        customerOrderStatusDTO.setEnteredBy(customerOrderStatus.getEnteredBy());
        customerOrderStatusDTO.setEnteredDate(customerOrderStatus.getEnteredDate());

        customerOrderStatusDTO.setUpdateBy(customerOrderStatus.getUpdateBy());
        customerOrderStatusDTO.setUpdateDate(customerOrderStatus.getUpdateDate());

        return customerOrderStatusDTO;
    }
}