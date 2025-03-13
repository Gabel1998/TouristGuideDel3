package com.example.touristguidedel3.Model;

import java.util.List;

public class Touristattraction {

    private int id;
    private String name;
    private String description;
    private City city;
    private List<Tag> tags;

    public Touristattraction() {}

    public Touristattraction(int id, String name, String description, City city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.city = city;
    }

    public Touristattraction(int id, String name, String description, City city, List<Tag> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
    }

    // Getters / Setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
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

    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;

    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
