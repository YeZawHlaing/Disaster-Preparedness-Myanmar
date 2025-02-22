package com.server.backend.dto;

import com.server.backend.entity.SupplyShop;

public class SupplyShopDto {
    private String shopName;
    private String contactNo;
    private String shopImage;
    private AddressDto address; // Embed AddressDTO

    public SupplyShopDto(SupplyShop shop) {
        this.shopName = shop.getShopName();
        this.contactNo = shop.getContactNo();
        this.shopImage = shop.getShopImage();
        this.address = new AddressDto(shop.getAddress());
    }

    public String getShopName() {
        return shopName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getShopImage() {
        return shopImage;
    }

    public AddressDto getAddress() {
        return address;
    }
}
