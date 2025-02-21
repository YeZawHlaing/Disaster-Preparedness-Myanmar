package com.server.backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "TrainingType")
public class TrainingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long volunteer_id;

    @Column(name = "trainingtype")
    private String trainingType;

    public long getVolunteer_id() {
        return volunteer_id;
    }

    public void setVolunteer_id(long volunteer_id) {
        this.volunteer_id = volunteer_id;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }
}
