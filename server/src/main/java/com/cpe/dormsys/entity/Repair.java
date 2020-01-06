package com.cpe.dormsys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
@Table(name = "REPAIR")

public class Repair {
    @Id
    @SequenceGenerator(name = "REPAIR_seq", sequenceName = "REPAIR_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPAIR_seq")
    @Column(name = "SREPAIR_ID", unique = true, nullable = true)

    private @NonNull Long id;

    @Column(name = "List")
    private @NonNull String  list;

    @Column(name = "Repair_Date")
    private @NonNull Date repairDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DeviceType.class)
    @JoinColumn(name = "DEVICETYPE_ID", insertable = true)
    private DeviceType type;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DeviceProblem.class)
    @JoinColumn(name = "DEVICEPROBLEMS_ID", insertable = true)
    private DeviceProblem problem;
    
    @ManyToOne(targetEntity = RoomBooking.class)
    @JoinColumn(name = "ROOMBOOKING_ID", insertable = true)
    private RoomBooking enrolled;

  

   

}