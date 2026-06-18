package com.dmsc.back_end.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicle_model")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_model_id")
    private int vehicleModelId;

    @Column(name = "vehicle_model_name")
    private String vehicleModelName;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "entered_by")
    private String enteredBy;

    @Column(name = "entered_date")
    private LocalDate enteredDate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_date")
    private LocalDate updateDate;

    @ManyToMany
    @JoinTable(
        name = "vehicle_model_has_vehicle_brand",
        joinColumns = @JoinColumn(name = "Vehicle_Model_vehicle_model_id"),
        inverseJoinColumns = @JoinColumn(name = "Vehicle_Brand_vehicle_brand_id")
    )
    private List<VehicleBrand> vehicleBrands = new ArrayList<>();
}
