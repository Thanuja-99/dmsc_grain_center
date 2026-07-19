package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.CustomerPaymentDTO;
import com.dmsc.back_end.entity.Billing;
import com.dmsc.back_end.entity.CustomerPayment;
import com.dmsc.back_end.repository.BillingRepository;
import com.dmsc.back_end.repository.CustomerPaymentRepository;
import com.dmsc.back_end.service.CustomerPaymentService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerPaymentServiceImpl implements CustomerPaymentService {

    @Autowired
    private CustomerPaymentRepository customerPaymentRepository;

    @Autowired
    private BillingRepository billingRepository;

    //================ CREATE ====================
    @Override
    public CustomerPaymentDTO create(CustomerPaymentDTO dto) {

        Billing billing = billingRepository.findById(dto.getBillId())
                .orElseThrow(() -> new RuntimeException("Billing not found"));

        CustomerPayment payment = new CustomerPayment();

        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());

        payment.setActive(true);

        payment.setEnteredBy(dto.getEnteredBy());
        payment.setEnteredDate(LocalDate.now());

        payment.setBilling(billing);

        CustomerPayment saved = customerPaymentRepository.save(payment);

        return toDTO(saved);
    }

    //================ UPDATE ====================
    @Override
    public CustomerPaymentDTO update(int id, CustomerPaymentDTO dto) {

        CustomerPayment payment = customerPaymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Payment not found"));

        Billing billing = billingRepository.findById(dto.getBillId())
                .orElseThrow(() -> new RuntimeException("Billing not found"));

        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());

        payment.setUpdateBy(dto.getUpdateBy());
        payment.setUpdateDate(LocalDate.now());

        payment.setBilling(billing);

        CustomerPayment updated = customerPaymentRepository.save(payment);

        return toDTO(updated);
    }

    //================ DELETE ====================
    @Override
    public void delete(int id) {

        CustomerPayment payment = customerPaymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Payment not found"));

        payment.setActive(false);

        customerPaymentRepository.save(payment);
    }

    //================ GET BY ID ====================
    @Override
    public CustomerPaymentDTO getById(int id) {

        CustomerPayment payment = customerPaymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Payment not found"));

        return toDTO(payment);
    }

    //================ GET ALL ====================
    @Override
    public List<CustomerPaymentDTO> getAll() {

        return customerPaymentRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //================ SEARCH BY BILL ====================
    @Override
    public List<CustomerPaymentDTO> searchByBill(Integer billId) {

        return customerPaymentRepository.findByBilling_BillId(billId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //================ SEARCH BY DATE ====================
    @Override
    public List<CustomerPaymentDTO> searchByDate(LocalDate paymentDate) {

        return customerPaymentRepository.findByPaymentDate(paymentDate)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //================ ENTITY -> DTO ====================
    private CustomerPaymentDTO toDTO(CustomerPayment payment) {

        CustomerPaymentDTO dto = new CustomerPaymentDTO();

        dto.setCustomerPaymentId(payment.getCustomerPaymentId());

        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());

        dto.setIsActive(payment.isActive());

        dto.setEnteredBy(payment.getEnteredBy());
        dto.setEnteredDate(payment.getEnteredDate());

        dto.setUpdateBy(payment.getUpdateBy());
        dto.setUpdateDate(payment.getUpdateDate());

        if (payment.getBilling() != null) {

            Billing billing = payment.getBilling();

            dto.setBillId(billing.getBillId());

            // Runtime Bill No
            dto.setBillNo("INV-" + String.format("%06d", billing.getBillId()));

            if (billing.getCustomer() != null) {

                dto.setCustomerId(billing.getCustomer().getCustomerId());
                dto.setCustomerName(billing.getCustomer().getCallingName());

            }
        }

        return dto;
    }

}