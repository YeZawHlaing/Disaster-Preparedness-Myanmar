package com.server.backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "TrainingPlan")
public class TrainingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long volunteerRole_id;

    @Column(name = "train_title")
    private String trainingTitle;

    @Column(name = "train_img")
    private String trainingImage;

    @Column(name = "description")
    private String description;

    @Column(name = "train_date")
    private String trainingDate;

    @Column(name = "deadLine")
    private String deadLine;

    public long getVolunteerRole_id() {
        return volunteerRole_id;
    }

    public void setVolunteerRole_id(long volunteerRole_id) {
        this.volunteerRole_id = volunteerRole_id;
    }

    public String getTrainingTitle() {
        return trainingTitle;
    }

    public void setTrainingTitle(String trainingTitle) {
        this.trainingTitle = trainingTitle;
    }

    public String getTrainingImage() {
        return trainingImage;
    }

    public void setTrainingImage(String trainingImage) {
        this.trainingImage = trainingImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(String trainingDate) {
        this.trainingDate = trainingDate;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }
}
