package com.server.backend.repository;

import com.server.backend.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepo extends JpaRepository<Address,Long> {
    @Query("SELECT a FROM Address a JOIN FETCH a.coordinate WHERE a.address_id = :id")
    Address findAddressWithCoordinate(@Param("id") long id);

}
