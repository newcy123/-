package com.cpe.dormsys.controller;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import com.cpe.dormsys.entity.EnrollVehicle;
import com.cpe.dormsys.entity.RoomBooking;
import com.cpe.dormsys.entity.Staff;
import com.cpe.dormsys.entity.VehicleType;
import com.cpe.dormsys.repository.EnrollVehicleRepository;
import com.cpe.dormsys.repository.RoomBookingRepository;
import com.cpe.dormsys.repository.StaffRepository;
import com.cpe.dormsys.repository.VehicleTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class EnrollVehicleController {

    @Autowired // เป็นการ connect ไปยัง database ซึ่งในที่นี้เราไม่ต้องเขียน connection database เอง
    private EnrollVehicleRepository enrollVehicleRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;
    @Autowired
    private RoomBookingRepository roomBookingRepository;

    @GetMapping("/enrolledVehicle")
    public Collection<EnrollVehicle> getEnrollVehicles() {
        return enrollVehicleRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/enrollVehicle/{staff_id}/{vehicle_Type_id}/{roomBooking_id}/{licensePlate}/{brandName}/{otherDetails}")
    public EnrollVehicle newEnrollVehicle(EnrollVehicle newEnrollVehicle,
    @PathVariable long staff_id,
    @PathVariable long vehicle_Type_id,
    @PathVariable long roomBooking_id,
    @PathVariable String licensePlate,
    @PathVariable String brandName,
    @PathVariable String otherDetails
    ) {

        Staff craetedBy = staffRepository.findById(staff_id);
        VehicleType typeOfVehicle = vehicleTypeRepository.findById(vehicle_Type_id);
        RoomBooking enrolledStudents = roomBookingRepository.findById(roomBooking_id);

        newEnrollVehicle.setCraetedBy(craetedBy);
        newEnrollVehicle.setTypeOfVehicle(typeOfVehicle);
        newEnrollVehicle.setEnrolledStudents(enrolledStudents);
        newEnrollVehicle.setEnrollDate(new Date());
        newEnrollVehicle.setLicensePlate(licensePlate);
        newEnrollVehicle.setBrandName(brandName);
        newEnrollVehicle.setOtherDetails(otherDetails);

        return enrollVehicleRepository.save(newEnrollVehicle);
    }
}