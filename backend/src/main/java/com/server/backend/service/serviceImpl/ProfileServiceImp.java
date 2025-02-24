package com.server.backend.service.serviceImpl;

import com.server.backend.entity.Address;
import com.server.backend.entity.Coordinate;
import com.server.backend.entity.Profile;
import com.server.backend.repository.AddressRepo;
import com.server.backend.repository.CoordinateRepo;
import com.server.backend.repository.ProfileRepo;
import com.server.backend.service.GeocodingService;
import com.server.backend.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;



@Service
public class ProfileServiceImp implements ProfileService {

    private static final String UPLOAD_DIR = "/app/images/";
    @Autowired
    private final ProfileRepo profileRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private CoordinateRepo coordinateRepo;

    @Autowired
    private GeocodingService geocodingService;
    public ProfileServiceImp(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }

    @Override
    public Profile createProfile(Profile prof, MultipartFile file) {
        Address address = prof.getAddress();

        // 🔥 Ensure Address is not null before processing
        if (address == null) {
            throw new RuntimeException("Address cannot be null");
        }

        // 🔹 Get Coordinates
        Coordinate coordinates = geocodingService.getCoordinatesFromAddress(
                address.getStreet() + ", " + (address.getCity() != null ? address.getCity() : "")
                        + ", " + (address.getState() != null ? address.getState() : "")
        );

        if (coordinates != null) {
            // 🔥 Save Coordinates explicitly
            Coordinate savedCoordinates = coordinateRepo.save(coordinates);
            address.setCoordinate(savedCoordinates);
        }

        // 🔥 Save Address explicitly
        Address savedAddress = addressRepo.save(address);
        prof.setAddress(savedAddress);

        // 🔥 Process Image Upload
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
                        .path("/Profile/uploads/")
                        .path(fileName)
                        .toUriString();

                prof.setProfileImage(imageUrl);

            } catch (IOException e) {
                throw new RuntimeException("Failed to store image", e);
            }
        }

        // 🔥 Save and return the supply shop
        return profileRepo.save(prof);
    }

    @Override
    public List<Profile> getAllProfile() {
        return profileRepo.findAll();
    }

    @Override
    public Profile deleteProfile(long id) {
        Profile is_exist=profileRepo.findById(id).orElseThrow(() -> new RuntimeException("Not Found in Menu"));
        profileRepo.deleteById(id);
        return is_exist;
    }

    @Override
    public Profile updateProf(Profile profile, long id) {
        Profile is_exist=profileRepo.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        is_exist.setAge(profile.getAge());
        is_exist.setContactNo(profile.getContactNo());
        is_exist.setGender(profile.getGender());
        is_exist.setProfile_id(profile.getProfile_id());
        profileRepo.save(is_exist);

        return is_exist;
    }
}
