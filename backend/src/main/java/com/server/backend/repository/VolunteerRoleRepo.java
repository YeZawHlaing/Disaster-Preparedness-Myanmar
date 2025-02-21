package com.server.backend.repository;

import com.server.backend.entity.VolunteerRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRoleRepo extends JpaRepository<VolunteerRole, Long> {
}
