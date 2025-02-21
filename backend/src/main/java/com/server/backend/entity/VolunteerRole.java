package com.server.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "VolunteerRole")
public class VolunteerRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long volunteerRole_id;

    @Column(name = "roleType")
    private String roleType;

    @Column(name = "request")
    private String roleTypeRequest;

    public long getVolunteerRole_id() {
        return volunteerRole_id;
    }

    public void setVolunteerRole_id(long volunteerRole_id) {
        this.volunteerRole_id = volunteerRole_id;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleTypeRequest() {
        return roleTypeRequest;
    }

    public void setRoleTypeRequest(String roleTypeRequest) {
        this.roleTypeRequest = roleTypeRequest;
    }
}
