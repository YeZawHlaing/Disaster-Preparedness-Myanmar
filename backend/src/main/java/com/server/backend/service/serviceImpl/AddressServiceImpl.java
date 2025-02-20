package com.server.backend.service.serviceImpl;

import com.server.backend.entity.Address;
import com.server.backend.repository.AddressRepo;
import com.server.backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class AddressServiceImpl implements AddressService {

    @Autowired
    private final AddressRepo addressRepo;

    public AddressServiceImpl(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }


    @Override
    public Address createAddress(Address address) {
        return addressRepo.save(address);
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepo.findAll();
    }

    @Override
    public Address deleteAddress(long id) {
        Address is_exist=addressRepo.findById(id).orElseThrow(() -> new RuntimeException("Not Found in Menu"));
        addressRepo.deleteById(id);
        return is_exist;
    }

    @Override
    public Address updateAddress(Address address, long id) {
        Address is_exist=addressRepo.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        is_exist.setAddress_id(address.getAddress_id());

        addressRepo.save(is_exist);

        return is_exist;
    }
}
