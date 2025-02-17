package com.server.backend.controller;



import com.server.backend.entity.SupplyShop;
import com.server.backend.service.SupplyShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;


@RestController
@RequestMapping("/supply-shop")
@CrossOrigin
public class SupplyShopController {

  //  private static final String UPLOAD_DIR = "E:/disaster preparedness/backend/src/main/resources/images";
  private static final String UPLOAD_DIR = "/app/images/";
        @Autowired
        private SupplyShopService supplyShopService;

//        @PostMapping("/register")
//        public ResponseEntity<SupplyShop> createShop(@RequestParam("shop_name") String shopName,
//                                                     @RequestParam("contact_no") String contactNo,
//                                                     @RequestParam("file") MultipartFile file) {
//            SupplyShop shop = supplyShopService.CreateShop(shopName, contactNo, file);
//            return new ResponseEntity<>(shop, HttpStatus.CREATED);
//        }

//    @PostMapping("/upload")
//    public ResponseEntity<SupplyShop> createShop(@RequestBody SupplyShop supplyShop){
//        SupplyShop shop = supplyShopService.CreateShop(supplyShop);
//        return new ResponseEntity<>(shop , HttpStatus.CREATED);
//
//    }

//    @PostMapping("/upload")
//    public ResponseEntity<SupplyShop> createShop(
//            @RequestParam("shopName") String shopName,
//            @RequestParam("contactNo") String contactNo,
//            @RequestParam("file") MultipartFile file) {
//
//        // Create a new SupplyShop object
//        SupplyShop supplyShop = new SupplyShop();
//        supplyShop.setShopName(shopName);
//        supplyShop.setContactNo(contactNo);
//
//        // Call the service to create the shop with image
//        SupplyShop createdShop = supplyShopService.CreateShop(supplyShop, file);
//
//        return new ResponseEntity<>(createdShop, HttpStatus.CREATED);
//    }
//

    @PostMapping(value = "/upload",consumes = "multipart/form-data")
    public ResponseEntity<SupplyShop> uploadShopData(
            @RequestPart("shopName") String shopName,
            @RequestPart("contactNo") String contactNo,
            @RequestPart("file") MultipartFile file) {

        SupplyShop supplyShop = new SupplyShop();
        supplyShop.setShopName(shopName);
        supplyShop.setContactNo(contactNo);

        // Process image and save shop data
        SupplyShop createdShop = supplyShopService.CreateShop(supplyShop, file);

        return ResponseEntity.ok(createdShop);
    }

        @GetMapping("/all")
        public ResponseEntity<List<SupplyShop>> getAllShops() {
            List<SupplyShop> shops = supplyShopService.getAllShop();
            return ResponseEntity.ok(shops);
        }

    @GetMapping("/uploads/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
            if (!Files.exists(filePath)) {
                return ResponseEntity.notFound().build();
            }

            Resource resource = new UrlResource(filePath.toUri());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    @PutMapping("/update/{id}")
        public ResponseEntity<SupplyShop> updateShop(@PathVariable long id,
                                                     @RequestBody SupplyShop shop) {
            SupplyShop updatedShop = supplyShopService.UpdateShop(shop, id);
            return ResponseEntity.ok(updatedShop);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteShop(@PathVariable long id) {
            supplyShopService.deleteShop(id);
            return ResponseEntity.ok("Shop deleted successfully");
        }
    }


