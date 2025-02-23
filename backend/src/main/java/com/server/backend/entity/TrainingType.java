package com.server.backend.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private long traningType_id;

    @Column(name = "trainingtype")
    private String trainingType;

//    @ManyToOne
//    @JoinColumn(name = "trainingPlan_id", nullable = false)
//    @JsonBackReference
//    private TrainingPlan trainingPlan;


    public long getTraningType_id() {
        return traningType_id;
    }

    public void setTraningType_id(long traningType_id) {
        this.traningType_id = traningType_id;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }


//    public TrainingPlan getTrainingPlan() {
//        return trainingPlan;
//    }
//
//    public void setTrainingPlan(TrainingPlan trainingPlan) {
//        this.trainingPlan = trainingPlan;
//    }


}
