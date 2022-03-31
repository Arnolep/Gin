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
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@Controller
public class BrandController {
    private final Logger logger = LoggerFactory.getLogger(BrandController.class);
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
    @GetMapping({"/brandlist/filter"})
    public String brandListWithFilter(Model model,
                                      @RequestParam(required = false) Integer minIntroduced,
                                      @RequestParam(required = false) Integer maxIntroduced) {
        logger.info(String.format("brandListWithFilter -- min=%b, max=%b", minIntroduced, maxIntroduced));

        List<Brand> brands = brandRepository.findByFilter(minIntroduced, maxIntroduced);

        model.addAttribute("brands", brands);
        model.addAttribute("minIntroduced", minIntroduced);
        model.addAttribute("maxIntroduced", maxIntroduced);
        model.addAttribute("showFilters", true);

        return "brandlist";
    }

}

