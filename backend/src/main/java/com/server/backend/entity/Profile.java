package com.server.backend.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long profile_id;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "age")
    private Long age;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "gender")
    private String gender;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonManagedReference
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    private User user;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    // Automatically saves the Address
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;


    public long getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(long profile_id) {
        this.profile_id = profile_id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
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
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
