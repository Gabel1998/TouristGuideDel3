package com.example.touristguidedel3.Service;


import com.example.touristguidedel3.Model.Tags;
import com.example.touristguidedel3.Model.Touristattraction;
import com.example.touristguidedel3.Repository.TouristattractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristattractionService {
    @Autowired
    private TouristattractionRepository touristattractionRepository;

    // GET Attraction By Name
    public Touristattraction getAttractionByName(String name){
        return touristattractionRepository.getAttractionByName(name);
    }

    // GET Attraction Tags
    public List<Tags> getAttractionsTags(String name) {
        List<Tags> listOfTags = touristattractionRepository.getAttractionsTags(name);
        return listOfTags;
    }
    // GET All Attractions
    public List<Touristattraction> getAllAttractions(){
       return touristattractionRepository.getAllAttractions();
    }
    // POST Save Attraction
    public Touristattraction saveAttraction(Touristattraction touristattraction){
        touristattractionRepository.saveAttraction(touristattraction);
        return touristattraction;
    }

    // POST Update Attraction
    public Touristattraction updateAttraction(Touristattraction touristattraction) {
        return touristattractionRepository.updateAttraction(touristattraction);
    }

    // POST Delete Attraction
    public Touristattraction deleteAttraction(String name) {
        return touristattractionRepository.deleteAttraction(name);
    }


}
