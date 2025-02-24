package com.server.backend.controller;


import com.server.backend.entity.TrainingPlan;

import com.server.backend.service.TrainingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/TrainingPlan")
@CrossOrigin
public class TrainingPlanController {

    private static final String UPLOAD_DIR = "/app/images/";
    @Autowired
    private TrainingPlanService trainingPlanService;

    @PostMapping(value = "/upload",consumes = "multipart/form-data")
    public ResponseEntity<TrainingPlan> uploadTrainingPlan(
            @RequestPart("trainingTitle") String trainingTitle,
            @RequestPart("description") String description,
            @RequestPart("trainingDate") String trainingDate,
            @RequestPart("deadLine") String deadLine ,
            @RequestPart("file") MultipartFile file) {

        TrainingPlan tplan = new TrainingPlan();
        tplan.setTrainingTitle(trainingTitle);
        tplan.setDescription(description);
        tplan.setTrainingDate(trainingDate);
        tplan.setDeadLine(deadLine);

        // Process image and save shop data
        TrainingPlan createtPlan = trainingPlanService.createTrainPlan(tplan , file);

        return ResponseEntity.ok(createtPlan);
    }




    @GetMapping("/all")
    public ResponseEntity<List<TrainingPlan>> getAllTrainingPlan() {
        List<TrainingPlan> tplan = trainingPlanService.getAllTrainPlan();
        return ResponseEntity.ok(tplan);
    }

    @GetMapping("/uploads/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
            if (!Files.exists(filePath)) {
                return ResponseEntity.notFound().build();
            }

            Resource resource = new UrlResource(filePath.toUri());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    @PutMapping("/update/{id}")
    public ResponseEntity<TrainingPlan> updateProf(@PathVariable long id,
                                              @RequestBody TrainingPlan p) {
        TrainingPlan updatedTPlan = trainingPlanService.updateTrainPlan(p, id);
        return ResponseEntity.ok(updatedTPlan);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProf(@PathVariable long id) {

        trainingPlanService.deleteTrainPlan(id);
        return ResponseEntity.ok("New deleted successfully");
    }

}
