package dk.experis.fakeitunes.controllers;

import dk.experis.fakeitunes.data_access.TrackRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ThymeleafCustomerController {
    private TrackRepository trep = new TrackRepository();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("tracks", trep.showcaseTracks());
        model.addAttribute("composers", trep.showcaseComposers());
        model.addAttribute("genres", trep.showcaseGenres());
        return "index";
    }

    /*@RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam (value = "trackName", required = false) String surname, Model model) {
        model.addAttribute("track", search);
        return "students";
    }*/

}
