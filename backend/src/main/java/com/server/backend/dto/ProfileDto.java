package com.server.backend.dto;

import com.server.backend.entity.Address;
import com.server.backend.entity.Profile;

public class ProfileDto {

    private String profileImage;
    private long age;
    private String contactNo;
    private String gender;
    private Address address;

    public ProfileDto(Profile profile){
        this.profileImage = profile.getProfileImage();
        this.age = profile.getAge();
        this.contactNo= profile.getContactNo();
        this.gender = profile.getGender();
    }

    public String getProfileImage() {
        return profileImage;
    }

    public long getAge() {
        return age;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getGender() {
        return gender;
    }

    public Address getAddress() {
        return address;
    }
}
