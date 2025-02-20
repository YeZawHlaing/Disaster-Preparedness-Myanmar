package com.server.backend.controller;

import com.server.backend.entity.User;
import com.server.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    public ResponseEntity<User> createRoom(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @GetMapping("/getUser")
    public List<User> getAllRoom(){
        return userService.getAllUser();
    }

//    @PutMapping("/updateRoom")
//    public ResponseEntity<User> updateRoom(@RequestParam (name = "id") long id, @RequestBody User room){
//        User updatedUser=userService.(room,id);
//        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/deleteRoom")
//    public ResponseEntity<Room> deleteRoom(@RequestParam (name = "id") long id){
//        Room deletedRoom=roomService.deleteRoom(id);
//        return new ResponseEntity<>(deletedRoom,HttpStatus.OK);
//    }

}
