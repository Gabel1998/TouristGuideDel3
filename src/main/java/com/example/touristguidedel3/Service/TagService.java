package com.example.touristguidedel3.Service;

import com.example.touristguidedel3.Model.Tag;
import com.example.touristguidedel3.Repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> findAllTags() {
        return tagRepository.findAllTags(); // Henter tags fra databasen
    }

    public Tag findTagById(int id){
        return tagRepository.findTagById(id);
    }
}
