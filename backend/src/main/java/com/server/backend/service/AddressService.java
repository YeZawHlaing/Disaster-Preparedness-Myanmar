package com.server.backend.service;

import com.server.backend.entity.Address;

import java.util.List;

public interface AddressService {
    Address createAddress(Address address);
    List<Address> getAllAddress();
    Address deleteAddress(long id);
    Address updateAddress(Address address , long id);

}
