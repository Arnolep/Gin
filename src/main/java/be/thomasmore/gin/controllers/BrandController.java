package be.thomasmore.gin.controllers;

import be.thomasmore.gin.model.Brand;
import be.thomasmore.gin.model.Recept;
import be.thomasmore.gin.repositories.BrandRepository;
import be.thomasmore.gin.repositories.ReceptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;
    @GetMapping({"/branddetails/{id}", "/branddetails"})

    public String branddetails(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "branddetails";

        Optional<Brand> brandFromDb = brandRepository.findById(id);
        if (brandFromDb.isPresent()) {
            model.addAttribute("brand", brandFromDb.get());
        }
        return "branddetails";
    }

    @GetMapping({"/branddetails/{id}/prev"})
    public String receptdetailsPrev(Model model, @PathVariable int id) {
        Optional<Brand> prevBrandFromDb = brandRepository.findFirstByIdLessThanOrderByIdDesc(id);
        if (prevBrandFromDb.isPresent())
            return String.format("redirect:/branddetails/%d", prevBrandFromDb.get().getId());
        Optional<Brand> lastBrandFromDb = brandRepository.findFirstByOrderByIdDesc();
        if (lastBrandFromDb.isPresent())
            return String.format("redirect:/branddetails/%d", lastBrandFromDb.get().getId());
        return "branddetails";
    }
    @GetMapping({"/branddetails/{id}/next"})
    public String branddetailsNext(Model model, @PathVariable int id) {
        Optional<Brand> nextBrandFromDB = brandRepository.findFirstByIdGreaterThanOrderByIdAsc(id);
        if (nextBrandFromDB.isPresent())
            return String.format("redirect:/branddetails/%d", nextBrandFromDB.get().getId());
        Optional<Brand> firstBrandFromDb = brandRepository.findFirstByOrderByIdAsc();
        if (firstBrandFromDb.isPresent())
            return String.format("redirect:/branddetails/%d", firstBrandFromDb.get().getId());
        return "branddetails";
    }
    @GetMapping({"/brandlist"})
    public String brandlist(Model model) {
        final Iterable<Brand> allbrands = brandRepository.findAll();
        model.addAttribute("brands", allbrands);
        return "brandlist";
    }
    @GetMapping("/brandlist/londondry/{filter}")
    public String receptenlistLondondryYes(Model model, @PathVariable String filter) {
        final Iterable<Brand> brands = brandRepository.findBylondondry(filter.equals("yes"));
        model.addAttribute("brands", brands);
        model.addAttribute("filterlondondry", filter.equals("yes") ? "yes" : "no");
        return "brandlist";
    }
    @GetMapping("/brandlist/spiced/{filter}")
    public String receptenlistSpicedYes(Model model, @PathVariable String filter) {
        final Iterable<Brand> brands = brandRepository.findByspiced(filter.equals("yes"));
        model.addAttribute("brands", brands);
        model.addAttribute("filterspiced", filter.equals("yes") ? "yes" : "no");
        return "brandlist";
    }

}

