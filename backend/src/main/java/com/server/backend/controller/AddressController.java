package com.server.backend.controller;

import com.server.backend.entity.Address;
import com.server.backend.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Address")
@CrossOrigin
@RequiredArgsConstructor
public class AddressController {
    @Autowired
    private AddressService addService;

    @PostMapping("/upload")
    public ResponseEntity<Address> createMenu(@RequestBody Address addr){
        Address saveAddr = addService.createAddress(addr);
        return new ResponseEntity<>(saveAddr, HttpStatus.CREATED);

    }
    @GetMapping("/getAddress")
    public List<Address> getAllMenu(){
        return addService.getAllAddress();
    }

    @PutMapping("updateAddress")
    public ResponseEntity<Address> updateMenu(@RequestParam (name ="id") long id, Address addr){
        Address updateAddr = addService.updateAddress(addr, id);
        return new ResponseEntity<>(updateAddr, HttpStatus.OK);

    }

    @DeleteMapping("deleteAddress")
    public ResponseEntity<Address> deleteMenu(@RequestParam (name = "id") long id){
        Address deleteAddr = addService.deleteAddress(id);
        return new ResponseEntity<>(deleteAddr , HttpStatus.OK);
    }
}
