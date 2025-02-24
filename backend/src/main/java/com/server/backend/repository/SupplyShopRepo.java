package com.server.backend.repository;

import com.server.backend.entity.SupplyShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplyShopRepo extends JpaRepository<SupplyShop,Long> {
    @Query(value = "SELECT shop_id,shop_name FROM supply_shop", nativeQuery = true)
    List<Object[]> findAllShopIdsAndNames();

    @Query(value = "SELECT * FROM supply_shop", nativeQuery = true)
    List<Object[]> findAllshopInfo();
}
