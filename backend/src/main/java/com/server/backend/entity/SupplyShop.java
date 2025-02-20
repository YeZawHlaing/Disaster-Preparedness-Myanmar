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

//    @Lob
//    @Column(name = "shop_image", columnDefinition = "LONGBLOB")
//    private byte[] shopImage;

    @Column(name = "shop_image")
    private String shopImage;

    @OneToOne(cascade = CascadeType.ALL) // Automatically saves the Address
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;





    public long getShopId() {
        return shopId;
    }
//
//    public byte[] getShopImage() {
//        return shopImage;
//    }
//
//    public void setShopImage(byte[] shopImage) {
//        this.shopImage = shopImage;
//    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
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
}
