package com.server.backend.service;

import com.server.backend.entity.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProfileService {

    Profile createProfile(Profile org, MultipartFile file);
    List<Profile> getAllProfile();
    Profile deleteProfile(long id);
    Profile updateProf(Profile profile , long id);

}
