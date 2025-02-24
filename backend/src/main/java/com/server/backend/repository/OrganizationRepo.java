package com.server.backend.repository;

import com.server.backend.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrganizationRepo extends JpaRepository<Organization , Long> {

    @Query(value = "SELECT * FROM organization", nativeQuery = true)
    List<Object[]> findAllOrgInfo();

}
