package com.server.backend.service.serviceImpl;

import com.server.backend.entity.Coordinate;
import com.server.backend.repository.CoordinateRepo;
import com.server.backend.service.CoordinateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinateServiceImpl implements CoordinateService {

    @Autowired
    private final CoordinateRepo coordinateRepo;

    public CoordinateServiceImpl(CoordinateRepo coordinateRepo) {
        this.coordinateRepo = coordinateRepo;
    }


    @Override
    public Coordinate createCoordinate(Coordinate coordinate) {
        return coordinateRepo.save(coordinate);
    }

    @Override
    public List<Coordinate> getAllCoordinate() {
        return coordinateRepo.findAll();
    }

    @Override
    public Coordinate updateCoordinate(Coordinate coordinate, Long id) {
        Coordinate is_exist=coordinateRepo.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        is_exist.setAddress(coordinate.getAddress());
        is_exist.setLatitude(coordinate.getLatitude());
        is_exist.setLongitude(coordinate.getLongitude());
        coordinateRepo.save(is_exist);

        return is_exist;
    }

    @Override
    public Coordinate deleteCoordinate(Long id) {
        Coordinate is_exist=coordinateRepo.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        coordinateRepo.deleteById(id);
        return is_exist;
    }
}
