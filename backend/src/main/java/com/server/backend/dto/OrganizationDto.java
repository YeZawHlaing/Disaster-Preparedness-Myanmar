package com.server.backend.dto;


import com.server.backend.entity.Address;
import com.server.backend.entity.Organization;
import com.server.backend.entity.SupplyShop;

public class OrganizationDto {

    private long org_id;
    private String name;

    private String orgType;

    private String licenseNo;

    private String date;

    private String mgContact;

    private String sos;

    private String socialUrl;

    private AddressDto address;



    public OrganizationDto(Organization organization) {
        this.org_id =organization.getOrg_id();
        this.name = organization.getName();
        this.orgType = organization.getOrgType();
        this.mgContact = organization.getMgContact();
        this.sos =organization.getSos();
        this.licenseNo=organization.getLicenseNo();
        this.date=organization.getDate();
        this.socialUrl=organization.getSocialUrl();
        this.address= new AddressDto(organization.getAddress());
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

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public long getOrg_id() {
        return org_id;
    }
}
