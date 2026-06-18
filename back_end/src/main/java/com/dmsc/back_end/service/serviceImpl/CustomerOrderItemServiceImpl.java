package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.CustomerOrderItemDTO;
import com.dmsc.back_end.entity.CustomerOrder;
import com.dmsc.back_end.entity.CustomerOrderItem;
import com.dmsc.back_end.entity.Item;
import com.dmsc.back_end.entity.Store;
import com.dmsc.back_end.repository.CustomerOrderItemRepository;
import com.dmsc.back_end.repository.CustomerOrderRepository;
import com.dmsc.back_end.repository.ItemRepository;
import com.dmsc.back_end.repository.StoreRepository;
import com.dmsc.back_end.service.CustomerOrderItemService;

import jakarta.transaction.Transactional;

@Service
public class CustomerOrderItemServiceImpl implements CustomerOrderItemService {

    @Autowired
    private CustomerOrderItemRepository customerOrderItemRepository;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StoreRepository storeRepository;

    // ================= GET BY ID =================
    @Override
    public CustomerOrderItemDTO getById(int id) {

        CustomerOrderItem customerOrderItem = customerOrderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Order Item not found"));

        return toDTO(customerOrderItem);
    }

    // ================= GET ALL =================
    @Override
    public List<CustomerOrderItemDTO> getAll() {

        return customerOrderItemRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= CREATE (WITH STOCK REDUCE) =================
    @Override
    @Transactional
    public CustomerOrderItemDTO create(CustomerOrderItemDTO customerOrderItemDTO) {

        CustomerOrder customerOrder = customerOrderRepository.findById(customerOrderItemDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Customer Order not found"));

        Item item = itemRepository.findById(customerOrderItemDTO.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        Store store = storeRepository.findById(customerOrderItemDTO.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        // ================= STOCK VALIDATION =================
        if (item.getQuantity() < customerOrderItemDTO.getQty()) {
            throw new RuntimeException("Insufficient stock for item: " + item.getItemName());
        }

        // ================= REDUCE STOCK =================
        item.setQuantity(item.getQuantity() - customerOrderItemDTO.getQty());
        itemRepository.save(item);

        CustomerOrderItem customerOrderItem = new CustomerOrderItem();

        customerOrderItem.setQty(customerOrderItemDTO.getQty());
        customerOrderItem.setUnitPrice(customerOrderItemDTO.getUnitPrice());

        customerOrderItem.setActive(true);

        customerOrderItem.setEnteredBy(customerOrderItemDTO.getEnteredBy());
        customerOrderItem.setEnteredDate(LocalDate.now());

        customerOrderItem.setCustomerOrder(customerOrder);
        customerOrderItem.setItem(item);
        customerOrderItem.setStore(store);

        CustomerOrderItem saved = customerOrderItemRepository.save(customerOrderItem);

        return toDTO(saved);
    }

    // ================= UPDATE =================
    @Override
    @Transactional
    public CustomerOrderItemDTO update(int id, CustomerOrderItemDTO customerOrderItemDTO) {

        CustomerOrderItem customerOrderItem = customerOrderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Order Item not found"));

        Item item = customerOrderItem.getItem();

        // OPTIONAL: adjust stock difference (basic version)
        int oldQty = customerOrderItem.getQty();
        int newQty = customerOrderItemDTO.getQty();

        int diff = newQty - oldQty;

        if (diff > 0 && item.getQuantity() < diff) {
            throw new RuntimeException("Insufficient stock for update");
        }

        item.setQuantity(item.getQuantity() - diff);
        itemRepository.save(item);

        customerOrderItem.setQty(newQty);
        customerOrderItem.setUnitPrice(customerOrderItemDTO.getUnitPrice());

        customerOrderItem.setUpdateBy(customerOrderItemDTO.getUpdateBy());
        customerOrderItem.setUpdateDate(LocalDate.now());

        CustomerOrderItem updated = customerOrderItemRepository.save(customerOrderItem);

        return toDTO(updated);
    }

    // ================= DELETE (RESTORE STOCK + SOFT DELETE) =================
    @Override
    public void delete(int id) {

        CustomerOrderItem customerOrderItem = customerOrderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Order Item not found"));

        Item item = customerOrderItem.getItem();

        // ================= RESTORE STOCK =================
        item.setQuantity(item.getQuantity() + customerOrderItem.getQty());
        itemRepository.save(item);

        customerOrderItem.setActive(false);

        customerOrderItemRepository.save(customerOrderItem);
    }

    // ================= DTO MAPPER =================
    private CustomerOrderItemDTO toDTO(CustomerOrderItem customerOrderItem) {

        CustomerOrderItemDTO customerOrderItemDTO = new CustomerOrderItemDTO();

        customerOrderItemDTO.setCustomerOrderItemId(customerOrderItem.getCustomerOrderItemId());
        customerOrderItemDTO.setQty(customerOrderItem.getQty());
        customerOrderItemDTO.setUnitPrice(customerOrderItem.getUnitPrice());

        customerOrderItemDTO.setActive(customerOrderItem.isActive());

        customerOrderItemDTO.setEnteredBy(customerOrderItem.getEnteredBy());
        customerOrderItemDTO.setEnteredDate(customerOrderItem.getEnteredDate());

        customerOrderItemDTO.setUpdateBy(customerOrderItem.getUpdateBy());
        customerOrderItemDTO.setUpdateDate(customerOrderItem.getUpdateDate());

        if (customerOrderItem.getCustomerOrder() != null) {
            customerOrderItemDTO.setOrderId(customerOrderItem.getCustomerOrder().getCustomerOrderId());
        }

        if (customerOrderItem.getItem() != null) {
            customerOrderItemDTO.setItemId(customerOrderItem.getItem().getItemId());
        }

        if (customerOrderItem.getStore() != null) {
            customerOrderItemDTO.setStoreId(customerOrderItem.getStore().getStoreId());
        }

        return customerOrderItemDTO;
    }
}