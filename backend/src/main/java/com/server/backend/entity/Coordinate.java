package com.server.backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "coordinate")
public class Coordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long coordinate_id;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @OneToOne(mappedBy = "coordinate", cascade = CascadeType.ALL)
    private Address address;

}
