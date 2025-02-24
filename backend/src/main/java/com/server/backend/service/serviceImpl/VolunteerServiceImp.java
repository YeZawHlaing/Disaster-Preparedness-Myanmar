package com.server.backend.service.serviceImpl;

import com.server.backend.entity.Address;
import com.server.backend.entity.User;
import com.server.backend.entity.Volunteer;
import com.server.backend.repository.UserRepo;
import com.server.backend.repository.VolunteerRepo;
import com.server.backend.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VolunteerServiceImp implements VolunteerService {

    @Autowired
    private final VolunteerRepo volunteerRepo;

    @Autowired
    UserRepo userRepo;

    public VolunteerServiceImp(VolunteerRepo volunteerRepo) {
        this.volunteerRepo = volunteerRepo;
    }
//    public Volunteer createVolunteer(Volunteer volunteer) {
//        // Extract username from the provided User object
//        User user = volunteer.getUser();
//        if (user == null || user.getUsername() == null) {
//            throw new RuntimeException("Username cannot be null");
//        }
//
//        // Find or save the user by username
//        User savedUser = userRepo.findByUsername(user.getUsername());
//
//
//        // Associate the user with the volunteer
//        volunteer.setUser(savedUser);
//
//        // Save the volunteer
//        return volunteerRepo.save(volunteer);
//    }

    public Volunteer createVolunteer(Volunteer volunteer) {
        // Extract username from the provided User object


        // Save volunteer
        return volunteerRepo.save(volunteer);
    }

    @Override
    public List<Volunteer> getAllVolunteer() {
        return volunteerRepo.findAll();
    }

    @Override
    public Volunteer deleteVolunteer(long id) {
        Volunteer is_exist=volunteerRepo.findById(id).orElseThrow(() -> new RuntimeException("Not Found in Menu"));
        volunteerRepo.deleteById(id);
        return is_exist;
    }

    @Override
    public Volunteer updateVolunteer(Volunteer volunteer, long id) {
        Volunteer is_exist=volunteerRepo.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        is_exist.setName(volunteer.getName());
        is_exist.setEmail(volunteer.getEmail());
        is_exist.setInstitute(volunteer.getInstitute());
        is_exist.setContactNo(volunteer.getContactNo());
        is_exist.setGender(volunteer.getGender());
        is_exist.setPurpose(volunteer.getPurpose());
        volunteerRepo.save(is_exist);

        return is_exist;
    }
}
