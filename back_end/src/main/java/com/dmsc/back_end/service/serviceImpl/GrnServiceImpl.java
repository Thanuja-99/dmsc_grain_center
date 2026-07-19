package com.dmsc.back_end.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmsc.back_end.dto.GrnDTO;
import com.dmsc.back_end.dto.GrnItemDTO;
import com.dmsc.back_end.entity.Grn;
import com.dmsc.back_end.entity.GrnItem;
import com.dmsc.back_end.entity.GrnStatus;
import com.dmsc.back_end.entity.Item;
import com.dmsc.back_end.entity.PurchaseOrder;
import com.dmsc.back_end.entity.Store;
import com.dmsc.back_end.entity.Supplier;
import com.dmsc.back_end.repository.GrnItemRepository;
import com.dmsc.back_end.repository.GrnRepository;
import com.dmsc.back_end.repository.GrnStatusRepository;
import com.dmsc.back_end.repository.ItemRepository;
import com.dmsc.back_end.repository.PurchaseOrderRepository;
import com.dmsc.back_end.repository.StoreRepository;
import com.dmsc.back_end.repository.SupplierRepository;
import com.dmsc.back_end.service.GrnService;

@Service
@Transactional
public class GrnServiceImpl implements GrnService {

    @Autowired
    private GrnRepository grnRepository;

    @Autowired
    private GrnItemRepository grnItemRepository;

    @Autowired
    private GrnStatusRepository grnStatusRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ItemRepository itemRepository;


    // create GRN

    @Override
    public GrnDTO createGrn(GrnDTO dto) {

        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(dto.getPurchaseOrderId())
                .orElseThrow(() -> new RuntimeException("Purchase Order not found"));

        GrnStatus status = grnStatusRepository.findById(dto.getGrnStatusId())
                .orElseThrow(() -> new RuntimeException("GRN Status not found"));

        Grn grn = new Grn();

        grn.setGrnDate(dto.getGrnDate());
        grn.setGrnNote(dto.getGrnNote());
        grn.setActive(true);
        grn.setEnteredBy(dto.getEnteredBy());
        grn.setEnteredDate(dto.getEnteredDate());
        grn.setUpdateBy(dto.getUpdateBy());
        grn.setUpdateDate(dto.getUpdateDate());

        grn.setSupplier(supplier);
        grn.setStore(store);
        grn.setPurchaseOrder(purchaseOrder);
        grn.setGrnStatus(status);

        Grn savedGrn = grnRepository.save(grn);

        List<GrnItem> itemList = new ArrayList<>();

        for (GrnItemDTO itemDTO : dto.getGrnItems()) {

            Item item = itemRepository.findById(itemDTO.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            GrnItem grnItem = new GrnItem();

            grnItem.setQtyRecived(itemDTO.getQtyRecived());
            grnItem.setActive(true);
            grnItem.setEnteredBy(dto.getEnteredBy());
            grnItem.setEnteredDate(dto.getEnteredDate());

            grnItem.setGrn(savedGrn);
            grnItem.setItem(item);

            grnItemRepository.save(grnItem);

            itemList.add(grnItem);

            // STOCK UPDATE

            Integer currentQty = item.getQuantity();

            if (currentQty == null) {
                currentQty = 0;
            }

            item.setQuantity(currentQty + itemDTO.getQtyRecived());

            itemRepository.save(item);

        }

        savedGrn.setGrnItems(itemList);

        return toDTO(savedGrn);

    }
    private GrnDTO toDTO(Grn grn){

        GrnDTO dto=new GrnDTO();

        dto.setGrnId(grn.getGrnId());
        dto.setGrnDate(grn.getGrnDate());
        dto.setGrnNote(grn.getGrnNote());

        dto.setIsActive(grn.isActive());

        dto.setEnteredBy(grn.getEnteredBy());
        dto.setEnteredDate(grn.getEnteredDate());

        dto.setUpdateBy(grn.getUpdateBy());
        dto.setUpdateDate(grn.getUpdateDate());

        dto.setSupplierId(grn.getSupplier().getSupplierId());
        dto.setSupplierName(grn.getSupplier().getSupplierName());

        dto.setStoreId(grn.getStore().getStoreId());
        dto.setStoreName(grn.getStore().getStoreName());

        dto.setPurchaseOrderId(grn.getPurchaseOrder().getPurchaseOrderId());

        dto.setGrnStatusId(grn.getGrnStatus().getGrnStatusId());
        dto.setGrnStatusName(grn.getGrnStatus().getGrnStatusName());

        List<GrnItemDTO> itemDTOList=new ArrayList<>();

        for(GrnItem item:grn.getGrnItems()){

                GrnItemDTO itemDTO=new GrnItemDTO();

                itemDTO.setGrnItemId(item.getGrnItemId());
                itemDTO.setQtyRecived(item.getQtyRecived());

                itemDTO.setItemId(item.getItem().getItemId());
                itemDTO.setItemName(item.getItem().getItemName());

                itemDTO.setGrnId(grn.getGrnId());

                itemDTOList.add(itemDTO);

        }

        dto.setGrnItems(itemDTOList);

        return dto;

   }
        //========================update GRN===================================
        @Override
        public GrnDTO updateGrn(int id, GrnDTO dto) {

        // Check whether the GRN exists
        Grn grn = grnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GRN not found"));

        // Business Rule:
        // GRN cannot be updated after it has been created.
        // This is because stock quantities have already been updated.
        // If changes are required, create a new GRN or use a Return/Adjustment process.

        throw new RuntimeException("GRN cannot be updated after confirmation.");

    }

        //========================== Soft Delete================================= 
        @Override
        public void deleteGrn(int id) {

        // Find the GRN
        Grn grn = grnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GRN not found"));

        // Business Rule:
        // Physical deletion is not allowed.
        // Only mark the record as inactive.

        grn.setActive(false);

        grnRepository.save(grn);

        }


        //=============================GetAll()Grns=============================
        @Override
        public List<GrnDTO> getAllGrns() {

        return grnRepository.findAll()
                .stream()
                .filter(Grn::isActive) // Return only active GRNs
                .map(this::toDTO)
                .collect(Collectors.toList());

        }


        //==============================getGrnById()==============================
        @Override
        public GrnDTO getGrnById(int id) {

        Grn grn = grnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GRN not found"));

        return toDTO(grn);

        }


        // ===============================searchGrns(int)===========================
        @Override
        public List<GrnDTO> searchGrns(int id) {

        return grnRepository.findAll()
                .stream()
                .filter(grn -> grn.getGrnId() == id)
                .map(this::toDTO)
                .collect(Collectors.toList());

        }

        //================================searchGrns(String)==========================

        @Override
        public List<GrnDTO> searchGrns(String note) {

        return grnRepository.findByGrnNoteContainingIgnoreCase(note)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        }
}
    