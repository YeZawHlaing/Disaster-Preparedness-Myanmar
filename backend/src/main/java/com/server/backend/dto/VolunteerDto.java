package com.server.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VolunteerDto {
    private long volunteerId;
    private String name;
    private String email;
    private String institute;
    private long contactNo;
    private String gender;
    private String purpose;
    private List<VolunteerRoleDto> volunteerRoles;  // List of volunteer roles
}

