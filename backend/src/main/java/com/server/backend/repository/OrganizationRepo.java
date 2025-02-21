package com.server.backend.repository;

import com.server.backend.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepo extends JpaRepository<Organization , Long> {
}
