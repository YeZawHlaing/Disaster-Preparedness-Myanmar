package com.server.backend.controller;


import com.server.backend.entity.Address;
import com.server.backend.entity.TrainingType;

import com.server.backend.entity.User;
import com.server.backend.entity.Volunteer;
import com.server.backend.repository.UserRepo;
import com.server.backend.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Volunteer")
@CrossOrigin
@RequiredArgsConstructor
public class VolunteerController {


    @Autowired
    UserRepo userRepo;
    @Autowired
    private VolunteerService volunteerService;

//    @PostMapping("/create")
//    public ResponseEntity<Volunteer> uploadOrgData(@RequestBody Volunteer vol) {
//       // Volunteer createdVol = volunteerService.createVolunteer(vol);
//        return null;
//    }

//    @PostMapping(value = "/upload", consumes = "multipart/form-data")
//    public ResponseEntity<Volunteer> createVol(@RequestBody Volunteer addr){
//        Volunteer saveAddr = volunteerService.createVolunteer(addr);
//        return new ResponseEntity<>(saveAddr, HttpStatus.CREATED);
//
//
//    }

    @PostMapping("/upload")
    public ResponseEntity<Volunteer> createBook(@RequestBody Volunteer volunteer) {
        Volunteer savedVolunteer = volunteerService.createVolunteer(volunteer);
        return new ResponseEntity<>(savedVolunteer, HttpStatus.CREATED);
    }



//    @PostMapping("/upload")
//    public ResponseEntity<Volunteer> createVolunteer(@RequestBody Volunteer volunteer) {
//        Volunteer savedVolunteer = volunteerService.createVolunteer(volunteer);
//        return new ResponseEntity<>(savedVolunteer, HttpStatus.CREATED);
//    }
    @GetMapping("/getVol")
    public List<Volunteer> getAllVolunteer(){
        return volunteerService.getAllVolunteer();
    }

    @PutMapping("updateVol")
    public ResponseEntity<Volunteer> updateVolunteer(@RequestParam (name ="id") long id, Volunteer v){
        Volunteer updateVol = volunteerService.updateVolunteer(v, id);
        return new ResponseEntity<>(updateVol, HttpStatus.OK);

    }

    @DeleteMapping("deleteVol")
    public ResponseEntity<Volunteer> deleteVolunteer(@RequestParam (name = "id") long id){
        Volunteer deleteVol = volunteerService.deleteVolunteer(id);
        return new ResponseEntity<>(deleteVol, HttpStatus.OK);
    }

}
