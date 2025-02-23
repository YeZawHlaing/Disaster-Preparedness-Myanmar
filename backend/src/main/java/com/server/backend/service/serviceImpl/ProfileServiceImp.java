package com.server.backend.service.serviceImpl;

import com.server.backend.entity.Address;
import com.server.backend.entity.Profile;
import com.server.backend.repository.ProfileRepo;
import com.server.backend.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProfileServiceImp implements ProfileService {

    @Autowired
    private final ProfileRepo profileRepo;

    public ProfileServiceImp(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }

    @Override
    public Profile createProfile(Profile prof, MultipartFile file) {
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
