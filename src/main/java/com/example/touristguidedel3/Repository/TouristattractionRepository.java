package com.example.touristguidedel3.Repository;


import mrrock.com.touristguidedel2.Model.Cities;
import mrrock.com.touristguidedel2.Model.Tags;
import mrrock.com.touristguidedel2.Model.Touristattraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristattractionRepository {
    private List<Touristattraction> touristattractionList = new ArrayList<>();


    private Tags[] testTags = {Tags.BIOGRAF,Tags.KULTURELT};

    public TouristattractionRepository() {

        touristattractionList.add(new Touristattraction("Tivoli", "Forlystelsespark i hjertet af København", new Tags[]{Tags.KULTURELT, Tags.BØRNEVENLIG}, Cities.KOBENHAVN));
        touristattractionList.add(new Touristattraction("Den Lille Havfrue", "Ikonisk statue ved Langelinie", new Tags[]{Tags.HISTORISK, Tags.GRATIS}, Cities.KOBENHAVN));
        touristattractionList.add(new Touristattraction("Legoland", "Temapark med LEGO-tema", new Tags[]{Tags.BØRNEVENLIG, Tags.KULTURELT}, Cities.BILLUND));
        touristattractionList.add(new Touristattraction("Aros Kunstmuseum", "Moderne kunstmuseum", new Tags[]{Tags.KULTURELT, Tags.MUSEUM}, Cities.AARHUS));
        touristattractionList.add(new Touristattraction("Skagen Gren", "Danmarks nordligste punkt", new Tags[]{Tags.NATUR, Tags.GRATIS}, Cities.SKAGEN));
    }


    // GET All Attractions
    public List<Touristattraction> getAllAttractions(){
        return touristattractionList;
    }

    // POST Save Attraction
    public Touristattraction saveAttraction(Touristattraction touristattraction){
        touristattractionList.add(touristattraction);
        return touristattraction;
    }

    // GET Attraction Tags
    public List<Tags> getAttractionsTags(String name) {
        List<Tags> foundTags = new ArrayList<>();
        for (Touristattraction attractions : touristattractionList) {
            if (attractions.getName().equalsIgnoreCase(name)) {
                for (Tags t : attractions.getTags()) {
                    foundTags.add(t);
                }
            }

        }
        return foundTags;
    }

    // POST Update Attraction
    public Touristattraction updateAttraction(Touristattraction touristattraction) {
        for (Touristattraction attraction : touristattractionList) {
            if (attraction.getId().equals(touristattraction.getId())) {
                attraction.setDescription(touristattraction.getDescription());
                attraction.setName(touristattraction.getName());
                attraction.setTags(touristattraction.getTags());
                attraction.setCity(touristattraction.getCity());
                return attraction;
            }
        }
        return null;
    }

    // GET Attraction By Name
    public Touristattraction getAttractionByName(String name) {
        for (Touristattraction t :touristattractionList) {
            if (name.equalsIgnoreCase(t.getName())){
                return t;
            }
        }
        return null;
    }

    // POST Delete Attraction
    public Touristattraction deleteAttraction(String name) {
        Touristattraction temp = null;
        for (Touristattraction t : touristattractionList) {
            if (t.getName().equalsIgnoreCase(name)) {
                temp = t;
                touristattractionList.remove(t);
                return temp;
            }
        }
        return temp;
    }
}
