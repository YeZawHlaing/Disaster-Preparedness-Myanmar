package com.server.backend.service.serviceImpl;


import com.server.backend.entity.TrainingPlan;
import com.server.backend.repository.TrainingPlanRepo;
import com.server.backend.service.TrainingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingPlanServiceImp implements TrainingPlanService {
    private static final String UPLOAD_DIR = "/app/images/";
    @Autowired
    private final TrainingPlanRepo trainPlanRepo;

    public TrainingPlanServiceImp(TrainingPlanRepo trainPlanRepo) {
        this.trainPlanRepo = trainPlanRepo;
    }


    @Override
    public TrainingPlan createTrainPlan(TrainingPlan tt, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + fileName);
                Files.createDirectories(filePath.getParent());
                Files.createDirectories(Paths.get(UPLOAD_DIR));

                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Generate full URL dynamically
                String imageUrl = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/TrainingPlan/uploads/")
                        .path(fileName)
                        .toUriString();

                tt.setTrainingImage(imageUrl); // Save full URL instead of just "/uploads/"

            } catch (IOException e) {
                throw new RuntimeException("Failed to store image", e);
            }
        }
        return trainPlanRepo.save(tt);
    }


    @Override
    public List<TrainingPlan> getAllTrainPlan() {
        return trainPlanRepo.findAll();
    }

    @Override
    public void deleteTrainPlan(long id) {
        Optional<TrainingPlan> news = trainPlanRepo.findById(id);
        if (news.isPresent()) {
            trainPlanRepo.deleteById(id);

            news.get();
        } else {
            throw new RuntimeException("Shop not found with id: " + id);
        }


    }

    @Override
    public TrainingPlan updateTrainPlan(TrainingPlan trainPlan, long id) {
        return trainPlanRepo.save(trainPlan);
    }
}

