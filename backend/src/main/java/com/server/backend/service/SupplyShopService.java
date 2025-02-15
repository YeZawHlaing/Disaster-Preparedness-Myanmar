package com.server.backend.service;

import com.server.backend.entity.SupplyShop;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SupplyShopService {
    SupplyShop CreateShop(String shop_name, String contact_no, MultipartFile file);

    List<SupplyShop> getAllShop();
    SupplyShop UpdateShop(SupplyShop shop , long id);
    void deleteShop(long id);


}
