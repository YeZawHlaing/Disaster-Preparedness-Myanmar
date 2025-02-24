package com.server.backend.controller;

import com.server.backend.dto.SupplyShopDto;
import com.server.backend.entity.Address;
import com.server.backend.entity.Coordinate;
import com.server.backend.entity.SupplyShop;
import com.server.backend.repository.AddressRepo;
import com.server.backend.repository.CoordinateRepo;
import com.server.backend.service.GeocodingService;
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
import java.util.Map;


@RestController
@RequestMapping("/supply-shop")
@CrossOrigin
public class SupplyShopController {

    //  private static final String UPLOAD_DIR = "E:/disaster preparedness/backend/src/main/resources/images";
    private static final String UPLOAD_DIR = "/app/images/";
    @Autowired
    private SupplyShopService supplyShopService;

    @Autowired
    private GeocodingService geocodingService;

    @Autowired
    CoordinateRepo coordinateRepo;

    @Autowired
    AddressRepo addressRepo;

//    @PostMapping(value = "/upload", consumes = "multipart/form-data")
//    public ResponseEntity<SupplyShop> uploadShopData(
//            @RequestPart("shopName") String shopName,
//            @RequestPart("contactNo") String contactNo,
//            @RequestPart("address") String address,
//            @RequestPart("file") MultipartFile file) {
//
//        // Convert address to coordinates
//        Coordinate coordinates = geocodingService.getCoordinatesFromAddress(address);
//
//        if (coordinates == null) {
//            System.out.println("⚠️ No coordinates found for address: " + address);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        System.out.println("📌 Latitude: " + coordinates.getLatitude() + ", Longitude: " + coordinates.getLongitude());
//
//
//
//
//
//        // 🔥 Explicitly Save Coordinate
//        Coordinate savedCoordinate = coordinateRepo.save(coordinates);
//        System.out.println("✅ Coordinate Saved with ID: " + savedCoordinate.getCoordinate_id());
//
//        Coordinate coordinateEt=new Coordinate();
//        coordinateEt.setLongitude(savedCoordinate.getLongitude());
//        coordinateEt.setLatitude(savedCoordinate.getLatitude());
//
//        // ✅ Create and Save Address
//        Address addressEntity = new Address();
//        addressEntity.setStreet(address);
//        addressEntity.setCoordinate(savedCoordinate);
//
//        Address savedAddress = addressRepo.save(addressEntity);
//
//        Address addressCoo = addressRepo.findAddressWithCoordinate(savedAddress.getAddress_id());
//
//        System.out.println("✅ Address Saved with ID: " + savedAddress.getAddress_id());
//
//
//        // ✅ Save the SupplyShop with the Address
//        SupplyShop supplyShop = new SupplyShop();
//        supplyShop.setShopName(shopName);
//        supplyShop.setContactNo(contactNo);
//        supplyShop.setAddress(savedAddress);
//
//
//        SupplyShop createdShop = supplyShopService.CreateShop(supplyShop, file);
//        return ResponseEntity.ok(createdShop);
//    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<SupplyShop> uploadShopData(
            @RequestParam("shopName") String shopName,
            @RequestParam("contactNo") String contactNo,
            @RequestParam("address") String address,
            @RequestParam("file") MultipartFile file) {

        // Convert address to coordinates
        Coordinate coordinates = geocodingService.getCoordinatesFromAddress(address);

        if (coordinates == null) {
            System.out.println("⚠️ No coordinates found for address: " + address);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        System.out.println("📌 Latitude: " + coordinates.getLatitude() + ", Longitude: " + coordinates.getLongitude());




        // 🔥 Explicitly Save Coordinate
        Coordinate savedCoordinate = coordinateRepo.save(coordinates);
        System.out.println("✅ Coordinate Saved with ID: " + savedCoordinate.getCoordinate_id());

        Coordinate coordinateEt=new Coordinate();
        coordinateEt.setLongitude(savedCoordinate.getLongitude());
        coordinateEt.setLatitude(savedCoordinate.getLatitude());

        // ✅ Create and Save Address
        Address addressEntity = new Address();
        addressEntity.setStreet(address);
        addressEntity.setCoordinate(savedCoordinate);

        Address savedAddress = addressRepo.save(addressEntity);

        Address addressCoo = addressRepo.findAddressWithCoordinate(savedAddress.getAddress_id());

        System.out.println("✅ Address Saved with ID: " + savedAddress.getAddress_id());


        // ✅ Save the SupplyShop with the Address
        SupplyShop supplyShop = new SupplyShop();

        supplyShop.setShopName(shopName);
        supplyShop.setContactNo(contactNo);
        supplyShop.setAddress(savedAddress);


        SupplyShop createdShop = supplyShopService.CreateShop(supplyShop, file);
        return ResponseEntity.ok(createdShop);
    }



@GetMapping("/all")
public ResponseEntity<List<SupplyShopDto>> getAllShops() {
    List<SupplyShop> shops = supplyShopService.getAllShop();

    // Convert list of SupplyShop entities to DTOs
    List<SupplyShopDto> shopDTOs = shops.stream()
            .map(SupplyShopDto::new)
            .toList(); // Java 16+ (Use `.collect(Collectors.toList())` for older versions)

    return ResponseEntity.ok(shopDTOs);
}


//    @GetMapping("/all")
//    public ResponseEntity<List<SupplyShop>> getAllShops() {
//        List<SupplyShop> shops = supplyShopService.getAllShop();
//        return ResponseEntity.ok(shops);
//    }

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


    @GetMapping("/ids-names")
    public List<Map<String, Object>> getShopIdsAndNames() {
        return supplyShopService.getAllShopIdsAndNames();
    }
    @GetMapping("/ids-all")
    public List<Map<String, Object>> getShopInfo() {
        return supplyShopService.getAllShopInfo();
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

//    @GetMapping("/{id}")
//    public ResponseEntity<SupplyShopDto> getShopById(@PathVariable long id) {
//        SupplyShop shop = supplyShopService.getShopById(id);
//        if (shop == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(new SupplyShopDto(shop));
//    }

}

