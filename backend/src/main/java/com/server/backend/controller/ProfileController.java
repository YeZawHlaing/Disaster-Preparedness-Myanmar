package com.server.backend.controller;


import com.server.backend.entity.*;
import com.server.backend.repository.AddressRepo;
import com.server.backend.repository.CoordinateRepo;
import com.server.backend.service.GeocodingService;
import com.server.backend.service.NewService;
import com.server.backend.service.OrganizationService;
import com.server.backend.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/Profile")
@CrossOrigin
public class ProfileController {

    private static final String UPLOAD_DIR = "/app/images/";
    @Autowired
    private ProfileService profService;

    @Autowired
    private GeocodingService geocodingService;

    @Autowired
    CoordinateRepo coordinateRepo;

    @Autowired
    AddressRepo addressRepo;


    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<Profile> uploadProfileData(
            @RequestParam("age") Long age,
            @RequestParam("contactNo") String contactNo,
            @RequestParam("gender") String gender,
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
//        SupplyShop supplyShop = new SupplyShop();
        Profile p=new Profile();

//        supplyShop.setShopName(shopName);
//        supplyShop.setContactNo(contactNo);
//        supplyShop.setAddress(savedAddress);

        p.setAge(age);
        p.setContactNo(contactNo);
        p.setGender(gender);
        p.setAddress(savedAddress);



        Profile createdp = profService.createProfile(p, file);
        return ResponseEntity.ok(createdp);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Profile>> getAllProfile() {
        List<Profile> prof = profService.getAllProfile();
        return ResponseEntity.ok(prof);
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
    public ResponseEntity<Profile> updateProf(@PathVariable long id,
                                          @RequestBody Profile p) {
        Profile updatedProf = profService.updateProf(p, id);
        return ResponseEntity.ok(updatedProf);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProf(@PathVariable long id) {

        profService.deleteProfile(id);
        return ResponseEntity.ok("New deleted successfully");
    }

}
