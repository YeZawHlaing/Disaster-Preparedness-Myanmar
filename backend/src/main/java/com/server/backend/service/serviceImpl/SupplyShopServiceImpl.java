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
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplyShopServiceImpl implements SupplyShopService {

//    private static final String UPLOAD_DIR = "E:/disaster preparedness/backend/src/main/resources/images/";
private static final String UPLOAD_DIR = "/app/images/";

//    @Value("${image-storage-directory}")
//    private String imageStorageDirectory;

        @Autowired
        private SupplyShopRepo supplyShopRepo;

        //image start


//
//    @Override
//    public SupplyShop CreateShop(SupplyShop supplyShop, MultipartFile file) {
//        if (file != null && !file.isEmpty()) {
//            try {
//                // Ensure directory exists
//                Files.createDirectories(Paths.get(UPLOAD_DIR));
//
//                // Generate unique filename
//                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//                Path filePath = Paths.get(UPLOAD_DIR + fileName);
//
//                // Copy file to the destination folder
//                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//                // Set image path in database
//                supplyShop.setShopImage("/uploads/" + fileName);
//
//            } catch (IOException e) {
//                throw new RuntimeException("Failed to store image", e);
//            }
//        }
//
//        return supplyShopRepo.save(supplyShop);
//    }

    public SupplyShop CreateShop(SupplyShop supplyShop, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + fileName);
                Files.createDirectories(filePath.getParent());
                Files.createDirectories(Paths.get(UPLOAD_DIR));

                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Generate full URL dynamically
                String imageUrl = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/supply-shop/uploads/")
                        .path(fileName)
                        .toUriString();

                supplyShop.setShopImage(imageUrl); // Save full URL instead of just "/uploads/"

            } catch (IOException e) {
                throw new RuntimeException("Failed to store image", e);
            }
        }

        return supplyShopRepo.save(supplyShop);
    }



    //image end


//        @Override
//        public SupplyShop CreateShop(SupplyShop supplyShop) {
//            SupplyShop shop = new SupplyShop();
//
//            shop.setShopName(shop_name);
//            shop.setContactNo(contact_no);
//            try {
//                shop.setImageData(file.getBytes());
//            } catch (IOException e) {
//                throw new RuntimeException("Failed to store image", e);
//            }
//
//            image start
//
//            image end
//              return   supplyShopRepo.save(supplyShop);
//
//            }


//    @Override
//    public SupplyShop CreateShop(SupplyShop supplyShop, MultipartFile file) {
//        // Setting basic details of the shop
//        supplyShop.setShopName(supplyShop.getShopName());
//        supplyShop.setContactNo(supplyShop.getContactNo());
//
//        // Handling the image upload
//        if (!file.isEmpty()) {
//            try {
//                // Convert the image file to byte array or save it as a URL (depending on your requirement)
//                byte[] imageBytes = file.getBytes();  // Use this if you plan to store the image as binary in DB
//                supplyShop.setShopImage(imageBytes);  // Save the binary image data
//
//                // Or you could save it as a URL if you're storing the image on an external server/cloud:
//                // String imageUrl = uploadToCloudService(file); // Implement a method for cloud upload
//                // supplyShop.setShopImage(imageUrl);
//
//            } catch (IOException e) {
//                throw new RuntimeException("Failed to store image", e);
//            }
//        }
//
//        // Save the shop with the image data
//        return supplyShopRepo.save(supplyShop);
//    }
//


        @Override
        public List<SupplyShop> getAllShop() {
            return supplyShopRepo.findAll();
        }

        @Override
        public SupplyShop UpdateShop(SupplyShop shop, long id) {
//            Optional<SupplyShop> existingShop = supplyShopRepo.findById(id);
//            if (existingShop.isPresent()) {
//                SupplyShop updatedShop = existingShop.get();
//                updatedShop.setShopName(shop.getShopName());
//                updatedShop.setContactNo(shop.getContactNo());
//            //    updatedShop.setImageData(shop.getImageData());
//              //  updatedShop.setAddress(shop.getAddress());
//                return supplyShopRepo.save(updatedShop);
//            } else {
//                throw new RuntimeException("Shop not found with id: " + id);
//            }
            return supplyShopRepo.save(shop);
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



