package be.thomasmore.gin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class SlideController {
        @GetMapping("/slideshow")
        public String Slideshow(){
            return "slideshow";
        }
    }

