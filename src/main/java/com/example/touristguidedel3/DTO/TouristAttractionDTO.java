package com.example.touristguidedel3.DTO;

import java.util.List;

public class TouristAttractionDTO {
    private int id;
    private String name;
    private int cityID;
    private List<Integer> tagsID;
    private String description;

    public TouristAttractionDTO() {
    }

    public TouristAttractionDTO(int id, String description, int cityID, String name, List<Integer> tagsID) {
        this.id = id;
        this.description = description;
        this.cityID = cityID;
        this.name = name;
        this.tagsID = tagsID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public List<Integer> getTagsID() {
        return tagsID;
    }

    public void setTagsID(List<Integer> tagsID) {
        this.tagsID = tagsID;
    }
}
