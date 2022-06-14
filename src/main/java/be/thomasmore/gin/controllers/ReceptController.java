package be.thomasmore.gin.controllers;

import be.thomasmore.gin.model.Recept;
import be.thomasmore.gin.model.User;
import be.thomasmore.gin.repositories.ReceptRepository;
import be.thomasmore.gin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class ReceptController {
    private final Logger logger = LoggerFactory.getLogger(ReceptController.class);
    @Autowired
    private ReceptRepository receptRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping({"/receptdetails1/{id}", "/receptdetails1", "/recepdetails1/{id}", "/recepdetails1"})

    public String receptdetails(Model model, @PathVariable(required = false) Integer id, Principal principal) {
        if (principal==null)
        {
            return "redirect:/login";
        }
        logger.info("activeUser="+principal.getName());
        Optional<User> activeUserOptional = userRepository.findUserByUsername(principal.getName());

        if(activeUserOptional.isEmpty())
        {
            return "redirect:/login";
        }

        User activeUser=activeUserOptional.get();
        model.addAttribute("favoritelist", activeUser.getFavorites());

        if (id == null) return "receptdetails1";

        Optional<Recept> receptFromDb = receptRepository.findById(id);
        if (receptFromDb.isPresent()) {
            model.addAttribute("recept", receptFromDb.get());
        }
        return "receptdetails1";
    }

    @GetMapping("receptfavorite/{id}")
    public String receptfavorite(Model model, @PathVariable int id, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        logger.info("activeUser=" + principal.getName());
        Optional<User> activeUserOptional = userRepository.findUserByUsername(principal.getName());

        if (activeUserOptional.isEmpty()) {
            return "redirect:/login";
        }

        User activeUser = activeUserOptional.get();

        Optional<Recept> favorite = receptRepository.findById(id);

        Collection<Recept> favorites = activeUser.getFavorites();

        if (!favorites.contains(favorite.get())) {
            favorites.add(favorite.get());
        }
        model.addAttribute("favoritelist", favorites.stream().toList());

        activeUser.setFavorites(favorites);
        userRepository.save(activeUser);
        return "redirect:/receptdetails1/" + id;

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
    public String receptdetailsNext(Model model, @PathVariable int id) {
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

    @GetMapping("/receptenlist/sugar/{filter}")
    public String receptenlistSugarYes(Model model, @PathVariable String filter) {
        final Iterable<Recept> recepten = receptRepository.findBySugar(filter.equals("yes"));
        model.addAttribute("recepten", recepten);
        model.addAttribute("filterSugar", filter.equals("yes") ? "yes" : "no");
        return "receptenlist";
    }

    @GetMapping({"/receptenlist"})
    public String receptenlist(Model model) {
        final int averagePrice = receptRepository.getAveragePrice();
        final int totalPrice = receptRepository.getTotalPrice();
        final Iterable<Recept> allRecepten = receptRepository.findAll();
        model.addAttribute("averagePrice", averagePrice);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("recepts", allRecepten);
        return "receptenlist";
    }

    @GetMapping({"/receptenlist/filter"})
    public String receptenListWithFilter(Model model,
                                         @RequestParam(required = false) Double minPrice,
                                         @RequestParam(required = false) Double maxPrice) {
        logger.info(String.format("receptenListWithFilter -- min=%b, max=%b", minPrice, maxPrice));

        List<Recept> recepts = receptRepository.findByFilter(minPrice, maxPrice);
        final int averagePrice = receptRepository.getAveragePrice();
        final int totalPrice = receptRepository.getTotalPrice();
        model.addAttribute("averagePrice", averagePrice);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("recepts", recepts);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("showFilters", true);

        return "receptenlist";
    }
}

