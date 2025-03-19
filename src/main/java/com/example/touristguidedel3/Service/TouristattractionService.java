package com.example.touristguidedel3.Service;

import com.example.touristguidedel3.Model.Tag;
import com.example.touristguidedel3.Model.Touristattraction;
import com.example.touristguidedel3.Repository.TouristattractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristattractionService {

    private final TouristattractionRepository touristattractionRepository;

    @Autowired
    public TouristattractionService(TouristattractionRepository touristattractionRepository) {
        this.touristattractionRepository = touristattractionRepository;
    }

    public Touristattraction getAttractionByName(String name){
        return touristattractionRepository.getAttractionByName(name);
    }

    public List<Tag> getAttractionsTags(int attractionId) {
        return touristattractionRepository.getAttractionsTags(attractionId);
    }

    public List<Touristattraction> getAllAttractions(){
        return touristattractionRepository.findAll();
    }

    public Touristattraction saveAttraction(Touristattraction touristattraction){
        return touristattractionRepository.saveAttraction(touristattraction);
    }

    public Touristattraction updateAttraction(Touristattraction touristattraction) {
        return touristattractionRepository.updateAttraction(touristattraction);
    }

    public Touristattraction deleteAttraction(String name) {
        return touristattractionRepository.deleteAttraction(name);
    }
}
