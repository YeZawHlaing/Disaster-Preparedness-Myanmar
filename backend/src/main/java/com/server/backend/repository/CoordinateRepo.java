package com.server.backend.repository;

import com.server.backend.entity.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinateRepo extends JpaRepository<Coordinate,Long> {


}
