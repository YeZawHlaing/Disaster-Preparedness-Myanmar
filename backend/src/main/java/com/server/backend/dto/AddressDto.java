package com.server.backend.dto;

import com.server.backend.entity.Address;

public class AddressDto {
    private long address_id;
    private String street;
    private String city;
    private String state;
    private CoordinateDto coordinate; // Embed CoordinateDTO

    public AddressDto(Address address) {
        this.address_id = address.getAddress_id();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.state = address.getState();
        this.coordinate = (address.getCoordinate() != null) ? new CoordinateDto(address.getCoordinate()) : null;
    }

    public long getAddress_id() {
        return address_id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public CoordinateDto getCoordinate() {
        return coordinate;
    }
}
