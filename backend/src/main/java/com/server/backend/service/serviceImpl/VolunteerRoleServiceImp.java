package com.server.backend.service.serviceImpl;

import com.server.backend.entity.VolunteerRole;
import com.server.backend.repository.VolunteerRoleRepo;
import com.server.backend.service.VolunteerRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VolunteerRoleServiceImp implements VolunteerRoleService {

    @Autowired
    private final VolunteerRoleRepo volunteerRoleRepo;

    public VolunteerRoleServiceImp(VolunteerRoleRepo volunteerRoleRepo) {
        this.volunteerRoleRepo = volunteerRoleRepo;
    }

    @Override
    public VolunteerRole createVolunteerRole(VolunteerRole volunteerRole) {
        return null;
    }

    @Override
    public List<VolunteerRole> getAllVolunteerRole() {
        return List.of();
    }

    @Override
    public VolunteerRole deleteVolunteerRole(long id) {
        return null;
    }

    @Override
    public VolunteerRole updateVolunteerRole(VolunteerRole volunteerRole, long id) {
        return null;
    }
}
