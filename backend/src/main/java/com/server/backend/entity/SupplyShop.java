package com.server.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "supply_shop")
public class SupplyShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long shopId;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "contact_no")
    private String contactNo;

    @Lob
    @Column(columnDefinition = "LONGBLOB", name = "shop_img")
    private byte[] imageData;

//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
//    private Address address;

    //getter setter

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
}
