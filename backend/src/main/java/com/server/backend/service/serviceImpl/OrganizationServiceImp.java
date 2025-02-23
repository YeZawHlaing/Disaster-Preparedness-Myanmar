package com.server.backend.service.serviceImpl;

import com.server.backend.entity.Address;
import com.server.backend.entity.Coordinate;
import com.server.backend.entity.Organization;
import com.server.backend.entity.User;
import com.server.backend.repository.AddressRepo;
import com.server.backend.repository.CoordinateRepo;
import com.server.backend.repository.OrganizationRepo;
import com.server.backend.repository.UserRepo;
import com.server.backend.service.GeocodingService;
import com.server.backend.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
@Service

public class OrganizationServiceImp implements OrganizationService {

    @Autowired
    private final OrganizationRepo orgRepo;

    public OrganizationServiceImp(OrganizationRepo orgRepo) {
        this.orgRepo = orgRepo;
    }


    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CoordinateRepo coordinateRepo;

    @Autowired
    private GeocodingService geocodingService;

    @Override
    public Organization createOrg(Organization org) {

//        Address address = org.getAddress();
//        User userr = org.getUser();
//
//        // 🔥 Ensure Address is not null before processing
//        if (address == null) {
//            throw new RuntimeException("Address cannot be null");
//        }
//
//        // 🔹 Get Coordinates
//        Coordinate coordinates = geocodingService.getCoordinatesFromAddress(
//                address.getStreet() + ", " + (address.getCity() != null ? address.getCity() : "")
//                        + ", " + (address.getState() != null ? address.getState() : "")
//        );
//
//        if (coordinates != null) {
//            // 🔥 Save Coordinates explicitly
//            Coordinate savedCoordinates = coordinateRepo.save(coordinates);
//            address.setCoordinate(savedCoordinates);
//        }
//
//        // 🔥 Save Address explicitly
//        Address savedAddress = addressRepo.save(address);
//        User saveUser = userRepo.save(userr);
//        org.setAddress(savedAddress);
//        org.setUser(saveUser);
//
//        // 🔥 Process Image Upload


        // 🔥 Save and return the supply shop
        return orgRepo.save(org);
    }

    @Override
    public List<Organization> getAllOrg() {
        return orgRepo.findAll();
    }

    @Override
    public Organization deleteOrg(long id) {
        Organization is_exist=orgRepo.findById(id).orElseThrow(() -> new RuntimeException("Not Found in Menu"));
        orgRepo.deleteById(id);
        return is_exist;
    }

    @Override
    public Organization updateOrg(Organization org, long id) {
        Organization is_exist=orgRepo.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        is_exist.setOrg_id(org.getOrg_id());
        is_exist.setName(org.getName());
        is_exist.setOrgType(org.getOrgType());
        is_exist.setLicenseNo(org.getLicenseNo());
        is_exist.setDate(org.getDate());
        is_exist.setMgContact(org.getMgContact());
        is_exist.setSos(org.getSos());
        is_exist.setSocialUrl(org.getSocialUrl());




        orgRepo.save(is_exist);

        return is_exist;
    }
}
