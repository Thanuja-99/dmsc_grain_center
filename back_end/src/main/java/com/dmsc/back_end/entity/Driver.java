package com.dmsc.back_end.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "driver")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private int  driverId;

    @Column(name = "driver_calling_name")
    private String driverCallingName;

    @Column(name = "driver_bd")
    private LocalDate driverBd;

    @Column(name = "driver_note")
    private String driverNote;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "entered_by")
    private String enteredBy;

    @Column(name = "entered_date")
    private LocalDate enteredDate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_date")
    private LocalDate updateDate;

    // @ManyToOne
    // @JoinColumn(name = "Driver_Type_driver_id")
    // private DriverType driverType;

    @ManyToOne
    @JoinColumn(name = "Gender_gender_id")
    private Gender gender;

    // @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    // private List<DriverPhoneNumber> phoneNumbers = new ArrayList<>();

}

