package com.server.backend.controller;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.backend.dto.OrganizationDto;
import com.server.backend.dto.SupplyShopDto;
import com.server.backend.entity.*;

import com.server.backend.repository.AddressRepo;
import com.server.backend.repository.CoordinateRepo;
import com.server.backend.repository.UserRepo;
import com.server.backend.service.GeocodingService;
import com.server.backend.service.OrganizationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<Organization> uploadOrgData(
            @RequestParam("name") String name,
            @RequestParam("orgType") String orgType,
            @RequestParam("licenseNo") String licenseNo,
            @RequestParam("date") String date,
            @RequestParam("mgContact") String mgContact,
            @RequestParam("sos") String sos,
            @RequestParam("address") String address,  // Address as string
            @RequestParam("socialUrl") String socialUrl
    ) {
        // 🔥 Convert String Address to Address Object
        Address addressEntity = new Address();
        addressEntity.setStreet(address);

        // 🔹 Get Coordinates
        Coordinate coordinates = geocodingService.getCoordinatesFromAddress(address);

        if (coordinates == null) {
            System.out.println("⚠️ No coordinates found for address: " + address);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        System.out.println("📌 Latitude: " + coordinates.getLatitude() + ", Longitude: " + coordinates.getLongitude());

//        // 🔥 Save Coordinate if not already saved
//        if (coordinates.getCoordinate_id() == null) {
//            coordinates = coordinateRepo.save(coordinates);
//        }
        addressEntity.setCoordinate(coordinates);

        // ✅ Save Address
        Address savedAddress = addressRepo.save(addressEntity);

        // ✅ Create and Save Organization
        Organization organization = new Organization();
        organization.setName(name);
        organization.setOrgType(orgType);
        organization.setLicenseNo(licenseNo);
        organization.setDate(date);
        organization.setMgContact(mgContact);
        organization.setSos(sos);
        organization.setAddress(savedAddress);
        organization.setSocialUrl(socialUrl);

        // ✅ Save Organization
        Organization createdOrganization = orgService.createOrg(organization);
        return ResponseEntity.ok(createdOrganization);
    }



    @GetMapping("/all")
    public ResponseEntity<List<OrganizationDto>> getAllShops() {
        List<Organization> organizations = orgService.getAllOrg();

        // Convert list of SupplyShop entities to DTOs
        List<OrganizationDto> orgDTOs = organizations.stream()
                .map(OrganizationDto::new)
                .toList(); // Java 16+ (Use `.collect(Collectors.toList())` for older versions)

        return ResponseEntity.ok(orgDTOs);
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
