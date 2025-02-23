package com.server.backend.controller;



import com.server.backend.entity.TrainingType;
import com.server.backend.service.TrainingTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TrainingType")
@CrossOrigin
@RequiredArgsConstructor
public class TrainingTypeController {

    @Autowired
    private TrainingTypeService trainTypeService;

    @PostMapping("/upload")
    public ResponseEntity<TrainingType> createTrainingType(@RequestBody TrainingType tt){
        TrainingType saveTrainType = trainTypeService.createTrainingType(tt);
        return new ResponseEntity<>(saveTrainType, HttpStatus.CREATED);

    }
    @GetMapping("/getTrainingType")
    public List<TrainingType> getAllTrainingType(){
        return trainTypeService.getAllTrainingType();
    }

    @PutMapping("updateTrainingType")
    public ResponseEntity<TrainingType> updateTrainingType(@RequestParam (name ="id") long id, TrainingType tt){
        TrainingType updatett = trainTypeService.updateTrainingType(tt, id);
        return new ResponseEntity<>(updatett, HttpStatus.OK);

    }

    @DeleteMapping("deleteTrainingType")
    public ResponseEntity<TrainingType> deleteTrainingType(@RequestParam (name = "id") long id){
        TrainingType deletett = trainTypeService.deletTrainingType(id);
        return new ResponseEntity<>(deletett, HttpStatus.OK);
    }


}
