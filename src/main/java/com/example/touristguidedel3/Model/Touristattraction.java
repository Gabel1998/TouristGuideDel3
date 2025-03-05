package com.example.touristguidedel3.Model;


import java.util.UUID;

public class Touristattraction {
    private UUID id =  UUID.randomUUID();
    private String name;
    private String description;
    private Tags[] tags;
    private Cities city;

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public Touristattraction() {
    }

    public Touristattraction(String name, String description) {
        this.name = name;
        this.description = description;
    }



    public Touristattraction(String name, String description, Tags[] tags) {
        this.name = name;
        this.description = description;
        this.tags = tags;
    }
    public Touristattraction(String name, String description, Tags[] tags, Cities city) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.city = city;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tags[] getTags() {
        return tags;
    }

    public void setTags(Tags[] tags) {
        this.tags = tags;
    }
}
