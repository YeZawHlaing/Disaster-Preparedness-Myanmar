package com.server.backend.service;

import com.server.backend.entity.TrainingPlan;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TrainingPlanService {
    TrainingPlan createTrainPlan(TrainingPlan trainingPlan, MultipartFile file);
    List<TrainingPlan> getAllTrainPlan();
    void deleteTrainPlan(long id);
    TrainingPlan updateTrainPlan(TrainingPlan trainPlan , long id);
}
