package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.CustomerReturnDTO;
import com.dmsc.back_end.entity.CustomerOrder;
import com.dmsc.back_end.entity.CustomerReturn;
import com.dmsc.back_end.entity.Item;
import com.dmsc.back_end.repository.CustomerOrderRepository;
import com.dmsc.back_end.repository.CustomerReturnRepository;
import com.dmsc.back_end.repository.ItemRepository;
import com.dmsc.back_end.service.CustomerReturnService;

import jakarta.transaction.Transactional;

@Service
public class CustomerReturnServiceImpl implements CustomerReturnService {

    @Autowired
    private CustomerReturnRepository customerReturnRepository;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private ItemRepository itemRepository;


     // ===================Create=================================
    @Override
    @Transactional
    public CustomerReturnDTO create(CustomerReturnDTO dto) {

        CustomerOrder customerOrder = customerOrderRepository
                .findById(dto.getCustomerOrderId())
                .orElseThrow(() -> new RuntimeException("Customer Order not found"));

        Item item = itemRepository
                .findById(dto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (dto.getQty() <= 0) {
            throw new RuntimeException("Return quantity must be greater than zero.");
        }

        // Increase Stock
        item.setQuantity(item.getQuantity() + dto.getQty());

        itemRepository.save(item);

        CustomerReturn customerReturn = new CustomerReturn();

        customerReturn.setReturnDate(dto.getReturnDate());
        customerReturn.setReturnReason(dto.getReturnReason());
        customerReturn.setQty(dto.getQty());

        customerReturn.setActive(true);

        customerReturn.setEnteredBy(dto.getEnteredBy());
        customerReturn.setEnteredDate(LocalDate.now());

        customerReturn.setCustomerOrder(customerOrder);
        customerReturn.setItem(item);

        CustomerReturn saved = customerReturnRepository.save(customerReturn);

        return toDTO(saved);
    }
        private CustomerReturnDTO toDTO(CustomerReturn customerReturn) {

        CustomerReturnDTO dto = new CustomerReturnDTO();

        dto.setCustomerReturnId(customerReturn.getCustomerReturnId());

        dto.setReturnDate(customerReturn.getReturnDate());

        dto.setReturnReason(customerReturn.getReturnReason());

        dto.setQty(customerReturn.getQty());

        dto.setIsActive(customerReturn.isActive());

        dto.setEnteredBy(customerReturn.getEnteredBy());

        dto.setEnteredDate(customerReturn.getEnteredDate());

        dto.setUpdateBy(customerReturn.getUpdateBy());

        dto.setUpdateDate(customerReturn.getUpdateDate());

        if (customerReturn.getCustomerOrder() != null) {

            dto.setCustomerOrderId(
                    customerReturn.getCustomerOrder().getCustomerOrderId());

        }

        if (customerReturn.getItem() != null) {

            dto.setItemId(customerReturn.getItem().getItemId());

            dto.setItemName(customerReturn.getItem().getItemName());

        }

        return dto;
    }

        // ===================Update==============================
        @Override
        @Transactional
        public CustomerReturnDTO update(int id, CustomerReturnDTO dto) {

            CustomerReturn customerReturn = customerReturnRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Customer Return not found"));

            Item item = customerReturn.getItem();

            // Restore old stock
            item.setQuantity(item.getQuantity() - customerReturn.getQty());

            if (dto.getQty() <= 0) {
                throw new RuntimeException("Return quantity must be greater than zero.");
            }

            // Add new return qty
            item.setQuantity(item.getQuantity() + dto.getQty());

            itemRepository.save(item);

            customerReturn.setReturnDate(dto.getReturnDate());
            customerReturn.setReturnReason(dto.getReturnReason());
            customerReturn.setQty(dto.getQty());

            customerReturn.setUpdateBy(dto.getUpdateBy());
            customerReturn.setUpdateDate(LocalDate.now());

            CustomerReturn updated = customerReturnRepository.save(customerReturn);

            return toDTO(updated);
        }

    // =====================Delete=========================

        @Override
        @Transactional
        public void delete(int id) {

            CustomerReturn customerReturn = customerReturnRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Customer Return not found"));

            Item item = customerReturn.getItem();

            // Remove returned quantity from stock
            item.setQuantity(item.getQuantity() - customerReturn.getQty());

            itemRepository.save(item);

            customerReturn.setActive(false);

            customerReturnRepository.save(customerReturn);
        }

    // =====================Get By ID========================

        @Override
        public CustomerReturnDTO getById(int id) {

            CustomerReturn customerReturn = customerReturnRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Customer Return not found"));

            return toDTO(customerReturn);
        }

    // =====================Get ALL============================

        @Override
        public List<CustomerReturnDTO> getAll() {

            return customerReturnRepository.findAll()
                    .stream()
                    .map(this::toDTO)
                    .collect(java.util.stream.Collectors.toList());
        }

    // =====================Search by order ID============================

        @Override
        public List<CustomerReturnDTO> searchByOrderId(int customerOrderId) {

            return customerReturnRepository
                    .findByCustomerOrder_CustomerOrderId(customerOrderId)
                    .stream()
                    .map(this::toDTO)
                    .collect(java.util.stream.Collectors.toList());
        }

     // =====================Search by Item Name============================

        @Override
        public List<CustomerReturnDTO> searchByItemName(String itemName) {

            return customerReturnRepository
                    .findByItem_ItemNameContainingIgnoreCase(itemName)
                    .stream()
                    .map(this::toDTO)
                    .collect(java.util.stream.Collectors.toList());
        }

     // =====================SEARCH BY RETURN DATE============================

        @Override
        public List<CustomerReturnDTO> searchByReturnDate(LocalDate returnDate) {

            return customerReturnRepository
                    .findByReturnDate(returnDate)
                    .stream()
                    .map(this::toDTO)
                    .collect(java.util.stream.Collectors.toList());
        }
}