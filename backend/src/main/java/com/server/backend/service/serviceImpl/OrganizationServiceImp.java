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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrganizationServiceImp implements OrganizationService {

    @Autowired
    private  OrganizationRepo orgRepo;

    @Autowired
    private  AddressRepo addressRepo;


    @Autowired
    private  CoordinateRepo coordinateRepo;

    @Autowired
    private  GeocodingService geocodingService;

    public OrganizationServiceImp(OrganizationRepo orgRepo, AddressRepo addressRepo, UserRepo userRepo, CoordinateRepo coordinateRepo, GeocodingService geocodingService) {
        this.orgRepo = orgRepo;
        this.addressRepo = addressRepo;
        this.coordinateRepo = coordinateRepo;
        this.geocodingService = geocodingService;
    }


    @Override
    public Organization createOrg(Organization organize) {
        Address address = organize.getAddress();

        if (address == null) {
            throw new RuntimeException("Address cannot be null");
        }

        // 🔹 Get Coordinates (Only if missing)
        if (address.getCoordinate() == null) {
            Coordinate coordinates = geocodingService.getCoordinatesFromAddress(
                    address.getStreet() + ", " +
                            (address.getCity() != null ? address.getCity() : "") + ", " +
                            (address.getState() != null ? address.getState() : "")
            );

            if (coordinates != null) {
                coordinates = coordinateRepo.save(coordinates);
                address.setCoordinate(coordinates);
            }
        }

        // 🔥 Save Address explicitly
        Address savedAddress = addressRepo.save(address);
        organize.setAddress(savedAddress);

        // 🔥 Save and return Organization
        return orgRepo.save(organize);
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
