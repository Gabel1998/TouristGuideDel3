package com.example.touristguidedel3.Controller;

import com.example.touristguidedel3.Model.Cities;
import com.example.touristguidedel3.Model.Tags;
import com.example.touristguidedel3.Model.Touristattraction;
import com.example.touristguidedel3.Service.TouristattractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
@RequestMapping("")
public class TouristattractionController {
    @Autowired
    private TouristattractionService  touristattractionService;

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
        List<Tags> listOfTags = touristattractionService.getAttractionsTags(name);
        Touristattraction touristattraction = touristattractionService.getAttractionByName(name);
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
    public String saveAttraction(@ModelAttribute Touristattraction touristattraction){
            touristattractionService.saveAttraction(touristattraction);
        return "redirect:/attractions";
    }

    // GET Add attraction
    @GetMapping("/attractions/add")
    public String showAddAttractionForm(Model model) {
        model.addAttribute("attractionList",touristattractionService.getAllAttractions());
        model.addAttribute("attraction", new Touristattraction());
        model.addAttribute("cities", Cities.values());
        model.addAttribute("tags", Tags.values()); // Enum værdier til Thymeleaf
        return "manage"; // Henviser til HTML-filen
    }

    // POST Update Attraction
    //Har ændret lidt i metoden, da den før brugte RequestBody, men nu bruger ModelAttribute, fordi en <form> ikke er komaptiable med RequestBody
    @PostMapping("/attractions/update")
    public String updateAttractions(@ModelAttribute Touristattraction touristattraction) {
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
            toBeEdited.setTags(new Tags[]{});
        }
        model.addAttribute("attraction",toBeEdited);
        model.addAttribute("cities", Cities.values());
        model.addAttribute("tags", Tags.values());
        return "editAttraction";
    }

}
