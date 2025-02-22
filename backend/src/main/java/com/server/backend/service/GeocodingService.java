package com.server.backend.service;

import com.server.backend.entity.Coordinate;

public interface GeocodingService {

    public Coordinate getCoordinatesFromAddress(String address);
}
