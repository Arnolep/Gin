package be.thomasmore.gin.controllers;

import be.thomasmore.gin.model.Recept;
import be.thomasmore.gin.repositories.ReceptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ReceptController {

    @Autowired
    private ReceptRepository receptRepository;
    @GetMapping({"/receptdetails1/{id}", "/receptdetails1"})

        public String receptdetails(Model model, @PathVariable(required = false) Integer id) {
            if (id == null) return "receptdetails1";

            Optional<Recept> receptFromDb = receptRepository.findById(id);
            if (receptFromDb.isPresent()) {
                model.addAttribute("recept", receptFromDb.get());
            }
            return "receptdetails1";
        }
    @GetMapping({"/receptdetails1/{id}/prev"})
    public String receptdetailsPrev(Model model, @PathVariable int id) {
        Optional<Recept> prevReceptFromDb = receptRepository.findFirstByIdLessThanOrderByIdDesc(id);
        if (prevReceptFromDb.isPresent())
            return String.format("redirect:/receptdetails1/%d", prevReceptFromDb.get().getId());
        Optional<Recept> lastReceptFromDb = receptRepository.findFirstByOrderByIdDesc();
        if (lastReceptFromDb.isPresent())
            return String.format("redirect:/receptdetails1/%d", lastReceptFromDb.get().getId());
        return "receptdetails1";
    }
    @GetMapping({"/receptdetails1/{id}/next"})
    public String venuedetailsNext(Model model, @PathVariable int id) {
        Optional<Recept> nextReceptFromDb = receptRepository.findFirstByIdGreaterThanOrderByIdAsc(id);
        if (nextReceptFromDb.isPresent())
            return String.format("redirect:/receptdetails1/%d", nextReceptFromDb.get().getId());
        Optional<Recept> firstReceptFromDb = receptRepository.findFirstByOrderByIdAsc();
        if (firstReceptFromDb.isPresent())
            return String.format("redirect:/receptdetails1/%d", firstReceptFromDb.get().getId());
        return "receptdetails1";
    }
    @GetMapping("/receptenlist/water/{filter}")
    public String receptenlistWaterYes(Model model, @PathVariable String filter) {
        final Iterable<Recept> recepten = receptRepository.findByWater(filter.equals("yes"));
        model.addAttribute("recepten", recepten);
        model.addAttribute("filterWater", filter.equals("yes") ? "yes" : "no");
        return "receptenlist";
    }
    @GetMapping({"/receptenlist"})
    public String receptenlist(Model model) {
        final Iterable<Recept> allRecepten = receptRepository.findAll();
        model.addAttribute("recepten", allRecepten);
        return "receptenlist";
    }
    }

