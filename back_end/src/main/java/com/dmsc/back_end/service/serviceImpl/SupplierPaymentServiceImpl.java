package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.SupplierPaymentDTO;
import com.dmsc.back_end.entity.Grn;
import com.dmsc.back_end.entity.SupplierPayment;
import com.dmsc.back_end.repository.GrnRepository;
import com.dmsc.back_end.repository.SupplierPaymentRepository;
import com.dmsc.back_end.service.SupplierPaymentService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplierPaymentServiceImpl implements SupplierPaymentService {

    @Autowired
    private SupplierPaymentRepository supplierPaymentRepository;

    @Autowired
    private GrnRepository grnRepository;

    //================ CREATE ====================
    @Override
    public SupplierPaymentDTO create(SupplierPaymentDTO dto) {

        Grn grn = grnRepository.findById(dto.getGrnId())
                .orElseThrow(() -> new RuntimeException("GRN not found"));

        SupplierPayment payment = new SupplierPayment();

        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());

        payment.setActive(true);

        payment.setEnteredBy(dto.getEnteredBy());
        payment.setEnteredDate(LocalDate.now());

        payment.setGrn(grn);

        SupplierPayment saved = supplierPaymentRepository.save(payment);

        return toDTO(saved);
    }

    //================ UPDATE ====================
    @Override
    public SupplierPaymentDTO update(int id, SupplierPaymentDTO dto) {

        SupplierPayment payment = supplierPaymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier Payment not found"));

        Grn grn = grnRepository.findById(dto.getGrnId())
                .orElseThrow(() -> new RuntimeException("GRN not found"));

        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());

        payment.setUpdateBy(dto.getUpdateBy());
        payment.setUpdateDate(LocalDate.now());

        payment.setGrn(grn);

        SupplierPayment updated = supplierPaymentRepository.save(payment);

        return toDTO(updated);
    }

    //================ DELETE ====================
    @Override
    public void delete(int id) {

        SupplierPayment payment = supplierPaymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier Payment not found"));

        payment.setActive(false);

        supplierPaymentRepository.save(payment);
    }

    //================ GET BY ID ====================
    @Override
    public SupplierPaymentDTO getById(int id) {

        SupplierPayment payment = supplierPaymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier Payment not found"));

        return toDTO(payment);
    }

    //================ GET ALL ====================
    @Override
    public List<SupplierPaymentDTO> getAll() {

        return supplierPaymentRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //================ SEARCH BY GRN ====================
    @Override
    public List<SupplierPaymentDTO> searchByGrn(Integer grnId) {

        return supplierPaymentRepository.findByGrn_GrnId(grnId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //================ SEARCH BY DATE ====================
    @Override
    public List<SupplierPaymentDTO> searchByDate(LocalDate paymentDate) {

        return supplierPaymentRepository.findByPaymentDate(paymentDate)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //================ ENTITY -> DTO ====================
    private SupplierPaymentDTO toDTO(SupplierPayment payment) {

        SupplierPaymentDTO dto = new SupplierPaymentDTO();

        dto.setSupplierPaymentId(payment.getSupplierPaymentId());

        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());

        dto.setIsActive(payment.isActive());

        dto.setEnteredBy(payment.getEnteredBy());
        dto.setEnteredDate(payment.getEnteredDate());

        dto.setUpdateBy(payment.getUpdateBy());
        dto.setUpdateDate(payment.getUpdateDate());

        if (payment.getGrn() != null) {

            Grn grn = payment.getGrn();

            dto.setGrnId(grn.getGrnId());

            if (grn.getSupplier() != null) {

                dto.setSupplierId(grn.getSupplier().getSupplierId());
                dto.setSupplierName(grn.getSupplier().getSupplierName());

            }
        }

        return dto;
    }

}