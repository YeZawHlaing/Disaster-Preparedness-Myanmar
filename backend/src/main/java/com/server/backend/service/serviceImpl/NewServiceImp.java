package com.server.backend.service.serviceImpl;

import com.server.backend.entity.New;
import com.server.backend.repository.NewRepo;
import com.server.backend.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service

public class NewServiceImp implements NewService {
    private static final String UPLOAD_DIR = "/app/images/";
    @Autowired
    public final NewRepo newRepo;

    public NewServiceImp(NewRepo newRepo) {
        this.newRepo = newRepo;
    }


    @Override
    public New createNew(New news, MultipartFile file) {
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
                        .path("/New/uploads/")
                        .path(fileName)
                        .toUriString();

                news.setNewImage(imageUrl); // Save full URL instead of just "/uploads/"

            } catch (IOException e) {
                throw new RuntimeException("Failed to store image", e);
            }
        }

        return newRepo.save(news);
    }

    @Override
    public List<New> getAllNew() {
        return newRepo.findAll();
    }

    @Override
    public void deleteNew(Long id) {
        Optional<New> news = newRepo.findById(id);
        if (news.isPresent()) {
            newRepo.deleteById(id);

            news.get();
        } else {
            throw new RuntimeException("Shop not found with id: " + id);
        }
    }



    @Override
    public New updateNew(New news, long id) {
        return newRepo.save(news);
    }




}
