package com.server.backend.service;

import com.server.backend.entity.User;
import com.server.backend.entity.Volunteer;

import java.util.List;

public interface VolunteerService {
    Volunteer createVolunteer(Volunteer vol);
    List<Volunteer> getAllVolunteer();
    Volunteer deleteVolunteer(long id);
    Volunteer updateVolunteer(Volunteer volunteer , long id);
}
