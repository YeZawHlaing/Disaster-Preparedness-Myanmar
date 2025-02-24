package com.server.backend.service;

import com.server.backend.entity.TrainingType;

import java.util.List;

public interface TrainingTypeService {
    TrainingType createTrainingType(TrainingType trainingType );
    List<TrainingType> getAllTrainingType();
    TrainingType deletTrainingType(long id);
    TrainingType updateTrainingType(TrainingType trainingType , long id);
}
