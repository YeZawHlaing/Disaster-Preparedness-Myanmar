package com.server.backend.service;

import com.server.backend.entity.Coordinate;

import java.util.List;

public interface CoordinateService {


    Coordinate createCoordinate(Coordinate coordinate);
    List<Coordinate> getAllCoordinate();
    Coordinate updateCoordinate(Coordinate coordinate, Long id);
    Coordinate deleteCoordinate(Long id);

}
