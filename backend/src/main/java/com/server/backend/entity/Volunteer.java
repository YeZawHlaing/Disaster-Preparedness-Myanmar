package com.server.backend.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Volunteer")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long volunteer_id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "institute")
    private String institute;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "gender")
    private String gender;

    @Column(name = "purpose")
    private String purpose;

//    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
//    private SupplyShop supply_shop;
//
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "coordinate_id", referencedColumnName = "coordinate_id")
//    private Coordinate coordinate;

//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
//    @JsonBackReference
//    private User user;

//    @OneToMany(mappedBy = "volunteer", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<VolunteerRole> volunteerRoles;


    public long getVolunteer_id() {
        return volunteer_id;
    }

    public void setVolunteer_id(long volunteer_id) {
        this.volunteer_id = volunteer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

//    public List<VolunteerRole> getVolunteerRoles() {
//        return volunteerRoles;
//    }
//
//    public void setVolunteerRoles(List<VolunteerRole> volunteerRoles) {
//        this.volunteerRoles = volunteerRoles;
//    }
}
