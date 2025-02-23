package com.server.backend.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long org_id;

    @Column(name = "name")
    private String name;


    @Column(name = "org_Type")
    private String orgType;


    @Column(name = "license_no")
    private String licenseNo;

    @Column(name = "date")
    private String date;


    @Column(name = "mg_contact")
    private String mgContact;


    @Column(name = "SOS")
    private String sos;


    @Column(name = "social_url")
    private String socialUrl;




//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonManagedReference
//    // Automatically saves the Address
//    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
//    private Address address;
//
//
//    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<TrainingPlan> trainingPlans;
//
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
//
//    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<VolunteerRole> volunteerRoles;

    public long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(long org_id) {
        this.org_id = org_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMgContact() {
        return mgContact;
    }

    public void setMgContact(String mgContact) {
        this.mgContact = mgContact;
    }

    public String getSos() {
        return sos;
    }

    public void setSos(String sos) {
        this.sos = sos;
    }

    public String getSocialUrl() {
        return socialUrl;
    }

    public void setSocialUrl(String socialUrl) {
        this.socialUrl = socialUrl;
    }


//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
//
//    public List<TrainingPlan> getTrainingPlans() {
//        return trainingPlans;
//    }
//
//    public void setTrainingPlans(List<TrainingPlan> trainingPlans) {
//        this.trainingPlans = trainingPlans;
//    }
//
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
//
//    public List<VolunteerRole> getVolunteerRoles() {
//        return volunteerRoles;
//    }
//
//    public void setVolunteerRoles(List<VolunteerRole> volunteerRoles) {
//        this.volunteerRoles = volunteerRoles;
//    }
}
