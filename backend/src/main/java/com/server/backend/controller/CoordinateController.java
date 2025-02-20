package com.server.backend.controller;

import com.server.backend.entity.Coordinate;
import com.server.backend.service.CoordinateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/coordinate/")
public class CoordinateController {

     @Autowired
    CoordinateService coordinateService;


    @PostMapping("/upload")
    public ResponseEntity<Coordinate> createCoordinate(@RequestBody Coordinate coordinate) {
        Coordinate savedCoordinate = coordinateService.createCoordinate(coordinate);
        return new ResponseEntity<>(savedCoordinate, HttpStatus.CREATED);
    }

    @GetMapping("/getCoordinate")
    public List<Coordinate> getAllCoordinate() {
        return coordinateService.getAllCoordinate();

    }

        @PutMapping("/updateCoordinate")
        public ResponseEntity<Coordinate> updateCoordinate(@RequestParam (name = "id") long id, @RequestBody Coordinate coordinate){
            Coordinate updateCoordinate=coordinateService.updateCoordinate(coordinate,id);
            return new ResponseEntity<>(updateCoordinate, HttpStatus.OK);
        }

        @DeleteMapping("/deleteCoordinate")
        public ResponseEntity<Coordinate> deleteCoordinate(@RequestParam (name = "id") long id){
            Coordinate deleteCoordinate=coordinateService.deleteCoordinate(id);
            return new ResponseEntity<>(deleteCoordinate,HttpStatus.OK);
        }
    }


