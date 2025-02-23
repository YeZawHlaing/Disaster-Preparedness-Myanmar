package com.server.backend.service;

import com.server.backend.entity.VolunteerRole;

import java.util.List;

public interface VolunteerRoleService {
    VolunteerRole createVolunteerRole(VolunteerRole volunteerRole );
    List<VolunteerRole> getAllVolunteerRole();
    VolunteerRole deleteVolunteerRole(long id);
    VolunteerRole updateVolunteerRole(VolunteerRole  volunteerRole , long id);
}
