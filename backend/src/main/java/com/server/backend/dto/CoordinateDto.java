package com.server.backend.dto;

import com.server.backend.entity.Coordinate;

public class CoordinateDto {
    private long coordinate_id;
    private double latitude;
    private double longitude;

    public CoordinateDto(Coordinate coordinate) {
        this.coordinate_id = coordinate.getCoordinate_id();
        this.latitude = coordinate.getLatitude();
        this.longitude = coordinate.getLongitude();
    }

    public long getCoordinate_id() {
        return coordinate_id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

