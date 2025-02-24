package com.server.backend.controller;


import com.server.backend.entity.New;
import com.server.backend.entity.Organization;
import com.server.backend.entity.Profile;
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

    @PostMapping(value = "/upload",consumes = "multipart/form-data")
    public ResponseEntity<Profile> uploadNewData(
            @RequestPart("age") Long age,
            @RequestPart("contactNo") String contactNo,
            @RequestPart("gender") String gender,



            @RequestPart("file") MultipartFile file) {

        Profile prof = new Profile();
        prof.setAge(age);
        prof.setContactNo(contactNo);
        prof.setGender(gender);



        // Process image and save shop data
        Profile createNews = profService.createProfile(prof , file);

        return ResponseEntity.ok(createNews);
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
