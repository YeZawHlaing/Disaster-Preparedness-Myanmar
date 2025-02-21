package com.server.backend.repository;

import com.server.backend.entity.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingTypeRepo extends JpaRepository<TrainingType , Long> {
}
