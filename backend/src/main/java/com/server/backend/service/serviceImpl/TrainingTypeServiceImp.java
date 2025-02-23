package com.server.backend.service.serviceImpl;

import com.server.backend.entity.Address;
import com.server.backend.entity.TrainingType;
import com.server.backend.entity.Volunteer;
import com.server.backend.repository.TrainingTypeRepo;
import com.server.backend.service.TrainingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrainingTypeServiceImp implements TrainingTypeService {

    @Autowired
    private final TrainingTypeRepo trainingTypeRepo;

    public TrainingTypeServiceImp(TrainingTypeRepo trainingTypeRepo) {
        this.trainingTypeRepo = trainingTypeRepo;
    }

    @Override
    public TrainingType createTrainingType(TrainingType trainingType) {
        return trainingTypeRepo.save(trainingType);
    }

    @Override
    public List<TrainingType> getAllTrainingType() {
        return trainingTypeRepo.findAll();
    }

    @Override
    public TrainingType deletTrainingType(long id) {
        TrainingType is_exist=trainingTypeRepo.findById(id).orElseThrow(() -> new RuntimeException("Not Found in Menu"));
        trainingTypeRepo.deleteById(id);
        return is_exist;
    }

    @Override
    public TrainingType updateTrainingType(TrainingType trainingType, long id) {
        TrainingType is_exist= trainingTypeRepo.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        is_exist.setTrainingType(trainingType.getTrainingType());
        is_exist.setTraningType_id(trainingType.getTraningType_id());

        trainingTypeRepo.save(is_exist);

        return is_exist;
    }
}
