package com.example.touristguidedel3.Service;


import com.example.touristguidedel3.Model.City;
import com.example.touristguidedel3.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;
    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> findAllCities(){
        return cityRepository.findAll();
    }


    public City findCityByID(int id){
        return cityRepository.findById(id);
    }
}
