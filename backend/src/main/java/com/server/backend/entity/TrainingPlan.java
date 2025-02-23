package com.server.backend.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "TrainingPlan")
public class TrainingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trainingPlan_id;

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


    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.PERSIST)
    @JoinColumn(name = "org_id" , referencedColumnName = "org_id")
    private Organization organization;

//    @ManyToOne
//    @JoinColumn(name = "organization_id", nullable = false)
//    @JsonBackReference
//    private Organization organization;

//    @OneToMany(mappedBy = "trainingPlan", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<TrainingType> trainingTypes;

    public long getTrainingPlan_id() {
        return trainingPlan_id;
    }

    public void setTrainingPlan_id(long trainingPlan_id) {
        this.trainingPlan_id = trainingPlan_id;
    }

//    public Organization getOrganization() {
//        return organization;
//    }
//
//    public void setOrganization(Organization organization) {
//        this.organization = organization;
//    }

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

//    public List<TrainingType> getTrainingTypes() {
//        return trainingTypes;
//    }
//
//    public void setTrainingTypes(List<TrainingType> trainingTypes) {
//        this.trainingTypes = trainingTypes;
//    }
}
