package com.server.backend.controller;



import com.server.backend.entity.*;

import com.server.backend.repository.AddressRepo;
import com.server.backend.repository.CoordinateRepo;
import com.server.backend.repository.UserRepo;
import com.server.backend.service.GeocodingService;
import com.server.backend.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/Organization")
@CrossOrigin
@RequiredArgsConstructor
public class OrganizationController {
    @Autowired
    private OrganizationService orgService;

    @Autowired
    private GeocodingService geocodingService;

    @Autowired
    CoordinateRepo coordinateRepo;

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    UserRepo userRepo;

//    @PostMapping(value = "/upload" , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<Organization> uploadOrgData(
//            @RequestPart("name") String name,
//            @RequestPart("orgType") String orgType,
//            @RequestPart("licenseNo") String licenseNo,
//            @RequestPart("date") String date,
//            @RequestPart("mgContact") String mgContact,
//            @RequestPart("sos") String sos,
//            @RequestPart("address") String address,
//            @RequestPart("socialUrl") String socialUrl,
//            @RequestPart("user") String user
//
//            )
//    {
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
//        User userEntity = new User();
//        userEntity.setUsername(user);
//
//        User saveUser = userRepo.save(userEntity);
//
//        // ✅ Save the SupplyShop with the Address
//        Organization org = new Organization();
//        org.setName(name);
//        org.setOrgType(orgType);
//        org.setLicenseNo(licenseNo);
//        org.setDate(date);
//        org.setMgContact(mgContact);
//        org.setSos(sos);
//        org.setSocialUrl(socialUrl);
//        org.setUser(saveUser);
//        org.setAddress(savedAddress);
//
//
//        Organization createdOrg = orgService.createOrg(org);
//        return ResponseEntity.ok(createdOrg);
//    }

    @PostMapping("/upload")
    public ResponseEntity<Organization> createOrg(@RequestBody Organization org) {
        Organization savedVolunteer = orgService.createOrg(org);
        return new ResponseEntity<>(savedVolunteer, HttpStatus.CREATED);
    }


    @GetMapping("/getOrg")
    public List<Organization> getAllOrg(){
        return orgService.getAllOrg();
    }

    @PutMapping("updateOrg")
    public ResponseEntity<Organization> updateOrg(@RequestParam (name ="id") long id, Organization org){
        Organization updateAddr = orgService.updateOrg(org, id);
        return new ResponseEntity<>(updateAddr, HttpStatus.OK);

    }

    @DeleteMapping("deleteOrg")
    public ResponseEntity<Organization> deleteOrg(@RequestParam (name = "id") long id){
        Organization deleteOrg = orgService.deleteOrg(id);
        return new ResponseEntity<>(deleteOrg, HttpStatus.OK);
    }

}
