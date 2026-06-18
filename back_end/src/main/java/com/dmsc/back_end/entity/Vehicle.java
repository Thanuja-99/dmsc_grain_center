package com.dmsc.back_end.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private int vehicleId;

    @Column(name = "vehicle_number_plate")
    private String vehicleNumberPlate;

    private Double capacity;

    @Column(name = "vehicle_note")
    private String vehicleNote;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "vehicle_model")
    private String vehicleModel;

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

    // FK: VehicleType (map kranwa)
    @ManyToOne
    @JoinColumn(name = "VEhicle_Type_vehicle_type_id")
    private VehicleType vehicleTypeRef;

    // FK: VehicleModel
    @ManyToOne
    @JoinColumn(name = "Vehicle_Model_vehicle_model_id")
    private VehicleModel vehicleModelRef;
}