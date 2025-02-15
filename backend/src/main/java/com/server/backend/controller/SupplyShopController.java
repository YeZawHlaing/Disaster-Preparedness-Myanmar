package com.server.backend.controller;



import com.server.backend.entity.SupplyShop;
import com.server.backend.service.SupplyShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/supply-shop")
@CrossOrigin
public class SupplyShopController {


        @Autowired
        private SupplyShopService supplyShopService;

        @PostMapping("/register")
        public ResponseEntity<SupplyShop> createShop(@RequestParam("shop_name") String shopName,
                                                     @RequestParam("contact_no") String contactNo,
                                                     @RequestParam("file") MultipartFile file) {
            SupplyShop shop = supplyShopService.CreateShop(shopName, contactNo, file);
            return new ResponseEntity<>(shop, HttpStatus.CREATED);
        }

        @GetMapping("/all")
        public ResponseEntity<List<SupplyShop>> getAllShops() {
            List<SupplyShop> shops = supplyShopService.getAllShop();
            return ResponseEntity.ok(shops);
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


