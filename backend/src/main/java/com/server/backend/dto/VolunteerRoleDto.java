package com.server.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VolunteerRoleDto {
    private long volunteerRoleId;
    private String roleType;
    private String roleTypeRequest;
}
