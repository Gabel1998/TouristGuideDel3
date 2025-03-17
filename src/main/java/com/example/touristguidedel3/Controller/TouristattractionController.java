package com.example.touristguidedel3.Controller;

import com.example.touristguidedel3.DTO.TouristAttractionDTO;
import com.example.touristguidedel3.Model.City;
import com.example.touristguidedel3.Model.Tag;
import com.example.touristguidedel3.Model.Touristattraction;
import com.example.touristguidedel3.Repository.CityRepository;
import com.example.touristguidedel3.Service.CityService;
import com.example.touristguidedel3.Service.TagService;
import com.example.touristguidedel3.Service.TouristattractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller()
@RequestMapping("")
public class TouristattractionController {
    @Autowired
    private TouristattractionService  touristattractionService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CityService cityService;
    // GET All Attractions
    @GetMapping("/attractions")
    public String getAllAttractions(Model model){
        model.addAttribute("attractionList",touristattractionService.getAllAttractions());
        return "attractions";
    }

    // GET Attraction By Name
    @GetMapping("/attractions/{name}")
    public String getAttractionByName(@PathVariable String name, Model model){
        model.addAttribute("attraction",touristattractionService.getAttractionByName(name));
        return "attraction";
    }

    // GET Attraction Tags
    @GetMapping("/attractions/{name}/tags")
    public String getAttractionsTags(@PathVariable String name, Model model) {
        Touristattraction touristattraction = touristattractionService.getAttractionByName(name);
        if (touristattraction == null) {
            return "redirect:/attractions";
        }

        List<Tag> listOfTags = touristattractionService.getAttractionsTags(touristattraction.getId());

        if (listOfTags.isEmpty()){
            model.addAttribute("tagList","Ikke nogle tags");
            model.addAttribute("attraction",touristattraction);
        }
        else{
            model.addAttribute("attraction",touristattraction);
            model.addAttribute("tagList",listOfTags);
        }
        return "attractionTags";
    }

    // POST Save Attraction
    @PostMapping("/attractions/save")
    public String saveAttraction(@ModelAttribute TouristAttractionDTO touristattractionDTO){
        Touristattraction touristattraction = new Touristattraction();
        touristattraction.setName(touristattractionDTO.getName());
        touristattraction.setDescription(touristattractionDTO.getDescription());
        touristattraction.setCity(cityService.findCityByID(touristattractionDTO.getCityID()));

        List<Tag> tagList = new ArrayList<>();
        if (touristattractionDTO.getTagsID() != null){
            for (int id : touristattractionDTO.getTagsID()){
                tagList.add(tagService.findTagById(id));
            }
        }

        touristattraction.setTags(tagList);

            touristattractionService.saveAttraction(touristattraction);
        return "redirect:/attractions";
    }

    // GET Add attraction
    @GetMapping("/attractions/add")
    public String showAddAttractionForm(Model model) {
        model.addAttribute("attractionList",touristattractionService.getAllAttractions());
        model.addAttribute("attraction", new TouristAttractionDTO());
        model.addAttribute("cities", cityService.findAllCities());
        model.addAttribute("tags", tagService.findAllTags()); // DB values skal ind her
        return "manage"; // Henviser til HTML-filen
    }

    // POST Update Attraction
    //Har ændret lidt i metoden, da den før brugte RequestBody, men nu bruger ModelAttribute, fordi en <form> ikke er komaptiable med RequestBody
    @PostMapping("/attractions/update")
    public String updateAttractions(@ModelAttribute TouristAttractionDTO touristAttractionDTO) {
Touristattraction touristattraction = new Touristattraction();
touristattraction.setId(touristAttractionDTO.getId());
touristattraction.setName(touristAttractionDTO.getName());
touristattraction.setDescription(touristAttractionDTO.getDescription());

City city = cityService.findCityByID(touristAttractionDTO.getCityID());
touristattraction.setCity(city);

List<Tag> tagList = new ArrayList<>();
if (touristAttractionDTO.getTagsID() != null){
    for (int id : touristAttractionDTO.getTagsID()){
        tagList.add(tagService.findTagById(id));
    }
}

touristattraction.setTags(tagList);

        touristattractionService.updateAttraction(touristattraction);
        return "redirect:/attractions/add"; // Tilbage til administrationssiden efter opdatering
    }


    // POST Delete Attraction
    @PostMapping("/attractions/delete/{name}")
    public String deleteAttraction(@PathVariable String name) {
        touristattractionService.deleteAttraction(name);
        return "redirect:/attractions";
    }

    @GetMapping("/attractions/edit/{name}")
    public String editAttraction(@PathVariable String name, Model model) {
        Touristattraction toBeEdited = touristattractionService.getAttractionByName(name);
        if (toBeEdited.getTags()==null){
            toBeEdited.setTags(new ArrayList<>());
        }
        model.addAttribute("attraction",toBeEdited);
        model.addAttribute("cities", cityService.findAllCities());
        model.addAttribute("tags", tagService.findAllTags()); //DB values skal tages her
        return "editAttraction";
    }

}
