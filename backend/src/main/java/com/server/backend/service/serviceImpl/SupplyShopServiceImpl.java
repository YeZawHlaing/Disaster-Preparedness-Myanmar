package com.server.backend.service.serviceImpl;


import com.server.backend.entity.SupplyShop;
import com.server.backend.repository.SupplyShopRepo;
import com.server.backend.service.SupplyShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplyShopServiceImpl implements SupplyShopService {


//    @Value("${image-storage-directory}")
//    private String imageStorageDirectory;

        @Autowired
        private SupplyShopRepo supplyShopRepo;


        @Override
        public SupplyShop CreateShop(String shop_name, String contact_no, MultipartFile file) {
            SupplyShop shop = new SupplyShop();

            shop.setShopName(shop_name);
            shop.setContactNo(contact_no);
            try {
                shop.setImageData(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Failed to store image", e);
            }

            //image start

            //image end
              return   supplyShopRepo.save(shop);

            }



        @Override
        public List<SupplyShop> getAllShop() {
            return supplyShopRepo.findAll();
        }

        @Override
        public SupplyShop UpdateShop(SupplyShop shop, long id) {
            Optional<SupplyShop> existingShop = supplyShopRepo.findById(id);
            if (existingShop.isPresent()) {
                SupplyShop updatedShop = existingShop.get();
                updatedShop.setShopName(shop.getShopName());
                updatedShop.setContactNo(shop.getContactNo());
                updatedShop.setImageData(shop.getImageData());
              //  updatedShop.setAddress(shop.getAddress());
                return supplyShopRepo.save(updatedShop);
            } else {
                throw new RuntimeException("Shop not found with id: " + id);
            }
        }

        @Override
        public void deleteShop(long id) {
            Optional<SupplyShop> shop = supplyShopRepo.findById(id);
            if (shop.isPresent()) {
                supplyShopRepo.deleteById(id);
                shop.get();
            } else {
                throw new RuntimeException("Shop not found with id: " + id);
            }
        }
    }



