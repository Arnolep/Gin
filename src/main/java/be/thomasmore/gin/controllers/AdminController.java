package be.thomasmore.gin.controllers;

import be.thomasmore.gin.model.Brand;
import be.thomasmore.gin.repositories.BrandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
@Autowired
private BrandRepository brandRepository;
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

@GetMapping("/brandedit/{id}")
    public String brandEdit(Model model, @PathVariable int id){
    logger.info("brandEdit "+id);
    Optional<Brand> brandFromDb = brandRepository.findById(id);
    if (brandFromDb.isPresent()) {
        model.addAttribute("brand", brandFromDb.get());
    }
    model.addAttribute("brands",brandRepository.findAll());
    return "admin/brandedit";
}
    @PostMapping("/brandedit/{id}")
    public String brandEditPost(Model model,
                                @PathVariable int id,
                                @ModelAttribute("brand") Brand brand) {
        logger.info("brandEditPost " + id + " -- new name= " + brand.getName());
        logger.info("brandEditPost " + id + " -- new description= " + brand.getDescription());
        logger.info("brandEditPost " + id + " -- new cocktailID= " + brand.getDescription());
        logger.info("brandEditPost " + id + " -- new introduced= " + brand.getDescription());
        brandRepository.save(brand);
        return "redirect:/branddetails/" + id;
       }
    @GetMapping("/brandnew")
    public String brandNew(Model model) {
        logger.info("brandNew ");
        model.addAttribute("brand", new Brand());
        return "admin/brandnew";
    }
    @PostMapping("/brandnew")
    public String brandNewPost(Model model,
                               @ModelAttribute("brand") Brand brand) {
        logger.info("brandNewPost -- new name=" + brand.getName() + ", description=" + brand.getDescription() +", cocktail=" +brand.getRecept()+", introduced= "+brand.getIntroduced());
        Brand newBrand = brandRepository.save(brand);
        return "redirect:/branddetails/" + newBrand.getId();
    }
}
