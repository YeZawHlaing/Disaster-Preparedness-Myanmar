package com.server.backend.repository;

import com.server.backend.entity.TrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingPlanRepo extends JpaRepository<TrainingPlan,Long > {
}
