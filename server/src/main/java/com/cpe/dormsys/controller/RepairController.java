package com.cpe.dormsys.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Date;
import com.cpe.dormsys.entity.RoomBooking;
import com.cpe.dormsys.entity.Repair;
import com.cpe.dormsys.entity.DeviceProblem;
import com.cpe.dormsys.entity.DeviceType;
import com.cpe.dormsys.repository.RoomBookingRepository;
import com.cpe.dormsys.repository.RepairRepository;
import com.cpe.dormsys.repository.DeviceTypeRepository;
import com.cpe.dormsys.repository.DeviceProblemRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class RepairController{
    
    @Autowired
    private final RepairRepository repairRepository;
    
    @Autowired
    private  DeviceTypeRepository deviceTypeRepository;

    @Autowired
    private  DeviceProblemRepository deviceProblemRepository;

    @Autowired
    private RoomBookingRepository roomBookingRepository;

    RepairController (RepairRepository repairRepository){
       this.repairRepository = repairRepository;
    }
    

    @GetMapping("/Repar")
    public Collection<Repair> getAllRepairs() { // collection ส่งกลับทั้งหมด
        return repairRepository.findAll().stream().collect(Collectors.toList());

    }

    @PostMapping("/Repair/{roomBooking_id}/{DeviceType_id}/{DeviceProblem_id}/{list}")
    public Repair newRepair(Repair newRepair,
    @PathVariable String list,
    @PathVariable long roomBooking_id,
    @PathVariable long DeviceType_id,
    @PathVariable long DeviceProblem_id)

    {
        
        DeviceType type = deviceTypeRepository.findById(DeviceType_id);
        DeviceProblem problem = deviceProblemRepository.findById(DeviceProblem_id);
        RoomBooking enrolled = roomBookingRepository.findById(roomBooking_id);

        newRepair.setEnrolled(enrolled);
        newRepair.setList(list);
        newRepair.setRepairDate(new Date());
        newRepair.setType(type);
        newRepair.setProblem(problem);

        return repairRepository.save(newRepair);


    }

    


}