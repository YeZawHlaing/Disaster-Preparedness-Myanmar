package com.server.backend.controller;


import com.server.backend.entity.TrainingType;
import com.server.backend.entity.VolunteerRole;
import com.server.backend.service.TrainingTypeService;
import com.server.backend.service.VolunteerRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/VolunteerRole")
@CrossOrigin
@RequiredArgsConstructor
public class VolunteerRoleController {

    @Autowired
    private VolunteerRoleService volunteerRService;

    @PostMapping("/upload")
    public ResponseEntity<VolunteerRole> createVolunteerRole(@RequestBody VolunteerRole vr){
        VolunteerRole saveVolR = volunteerRService.createVolunteerRole(vr);
        return new ResponseEntity<>(saveVolR, HttpStatus.CREATED);

    }
    @GetMapping("/getVolR")
    public List<VolunteerRole> getAllVolunteerRole(){
        return volunteerRService.getAllVolunteerRole();
    }

    @PutMapping("updateTrainingType")
    public ResponseEntity<VolunteerRole> updateVolunteerRole(@RequestParam (name ="id") long id, VolunteerRole vr){
        VolunteerRole updatetVr = volunteerRService.updateVolunteerRole(vr, id);
        return new ResponseEntity<>(updatetVr, HttpStatus.OK);

    }

    @DeleteMapping("deleteTrainingType")
    public ResponseEntity<VolunteerRole> deleteVolunteerRole(@RequestParam (name = "id") long id){
        VolunteerRole deletetVr = volunteerRService.deleteVolunteerRole(id);
        return new ResponseEntity<>(deletetVr, HttpStatus.OK);
    }

}
