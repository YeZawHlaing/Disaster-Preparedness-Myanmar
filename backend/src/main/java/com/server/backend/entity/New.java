package com.server.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "New")
public class New {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long newId;


    @Column(name = "new_title")
    private String newTitle;


    @Column(name = "sub_title")
    private String subTitle;


    @Column(name = "news_imge")
    private String newsImage;



    @Column(name = "news_description")
    private String description;

    public void setNewImage(String imageUrl) {
        this.newsImage = imageUrl;
    }


    public void setNewId(long newId) {
        this.newId = newId;
    }
    public Long getNewId(){
        return newId;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }



    public String getDescription() {
        return description;
    }

    public String getNewsImage() {
        return newsImage;
    }
}
