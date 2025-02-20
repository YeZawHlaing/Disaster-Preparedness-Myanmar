package com.server.backend.repository;

import com.server.backend.entity.New;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewRepo extends JpaRepository<New, Long> {
}
