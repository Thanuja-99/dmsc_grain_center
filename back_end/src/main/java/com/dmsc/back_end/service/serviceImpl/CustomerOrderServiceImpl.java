package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.CustomerOrderDTO;
import com.dmsc.back_end.entity.Customer;
import com.dmsc.back_end.entity.CustomerOrder;
import com.dmsc.back_end.entity.CustomerOrderItem;
import com.dmsc.back_end.entity.CustomerOrderStatus;
import com.dmsc.back_end.repository.CustomerOrderItemRepository;
import com.dmsc.back_end.repository.CustomerOrderRepository;
import com.dmsc.back_end.repository.CustomerOrderStatusRepository;
import com.dmsc.back_end.repository.CustomerRepository;
import com.dmsc.back_end.service.CustomerOrderService;

import jakarta.transaction.Transactional;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerOrderStatusRepository customerOrderStatusRepository;

    @Autowired
    private CustomerOrderItemRepository customerOrderItemRepository;

    // ================= GET BY ID =================
    @Override
    public CustomerOrderDTO getById(int id) {

        CustomerOrder customerOrder = customerOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Order not found"));

        return toDTO(customerOrder);
    }

    // ================= GET ALL =================
    @Override
    public List<CustomerOrderDTO> getAll() {

        return customerOrderRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= CREATE =================
    @Override
    @Transactional
    public CustomerOrderDTO create(CustomerOrderDTO customerOrderDTO) {

        Customer customer = customerRepository.findById(customerOrderDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        CustomerOrderStatus status = customerOrderStatusRepository
                .findById(customerOrderDTO.getStatusId())
                .orElseThrow(() -> new RuntimeException("Order Status not found"));

        CustomerOrder customerOrder = new CustomerOrder();

        customerOrder.setCustomerOrderDate(customerOrderDTO.getCustomerOrderDate());
        customerOrder.setActive(true);

        customerOrder.setEnteredBy(customerOrderDTO.getEnteredBy());
        customerOrder.setEnteredDate(LocalDate.now());

        customerOrder.setCustomer(customer);
        customerOrder.setStatus(status);

        CustomerOrder savedOrder = customerOrderRepository.save(customerOrder);

        return toDTO(savedOrder);
    }

    // ================= UPDATE =================
    @Override
    @Transactional
    public CustomerOrderDTO update(int id, CustomerOrderDTO customerOrderDTO) {

        CustomerOrder customerOrder = customerOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Order not found"));

        if (customerOrderDTO.getCustomerId() != null) {

            Customer customer = customerRepository.findById(customerOrderDTO.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            customerOrder.setCustomer(customer);
        }

        if (customerOrderDTO.getStatusId() != null) {

            CustomerOrderStatus status = customerOrderStatusRepository
                    .findById(customerOrderDTO.getStatusId())
                    .orElseThrow(() -> new RuntimeException("Order Status not found"));

            customerOrder.setStatus(status);
        }

        customerOrder.setCustomerOrderDate(customerOrderDTO.getCustomerOrderDate());

        customerOrder.setUpdateBy(customerOrderDTO.getUpdateBy());
        customerOrder.setUpdateDate(LocalDate.now());

        CustomerOrder updated = customerOrderRepository.save(customerOrder);

        return toDTO(updated);
    }

    // ================= DELETE (SOFT DELETE) =================
    @Override
    public void delete(int id) {

        CustomerOrder customerOrder = customerOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Order not found"));

        customerOrder.setActive(false);

        customerOrderRepository.save(customerOrder);
    }

    // ================= ENTITY -> DTO =================
    private CustomerOrderDTO toDTO(CustomerOrder customerOrder) {

        CustomerOrderDTO customerOrderDTO = new CustomerOrderDTO();

        customerOrderDTO.setCustomerOrderId(customerOrder.getCustomerOrderId());
        customerOrderDTO.setCustomerOrderDate(customerOrder.getCustomerOrderDate());

        customerOrderDTO.setActive(customerOrder.isActive());

        customerOrderDTO.setEnteredBy(customerOrder.getEnteredBy());
        customerOrderDTO.setEnteredDate(customerOrder.getEnteredDate());

        customerOrderDTO.setUpdateBy(customerOrder.getUpdateBy());
        customerOrderDTO.setUpdateDate(customerOrder.getUpdateDate());

        if (customerOrder.getCustomer() != null) {

            customerOrderDTO.setCustomerId(customerOrder.getCustomer().getCustomerId());
        }

        if (customerOrder.getStatus() != null) {

            customerOrderDTO.setStatusId(customerOrder.getStatus().getStatusId());
        }

        if (customerOrder.getItems() != null) {

            customerOrderDTO.setOrderItemIds(
                    customerOrder.getItems()
                            .stream()
                            .map(CustomerOrderItem::getCustomerOrderItemId)
                            .collect(Collectors.toList())
            );
        }

        return customerOrderDTO;
    }
}