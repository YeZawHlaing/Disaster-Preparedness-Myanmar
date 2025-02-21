package com.server.backend.repository;

import com.server.backend.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepo extends JpaRepository<Volunteer , Long> {
}
