package be.thomasmore.gin.controllers;

import be.thomasmore.gin.model.User;
import be.thomasmore.gin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.regex.Pattern;
@Controller
public class UserController {
   @Autowired
    private UserRepository userRepository;
   @Autowired
    private PasswordEncoder passwordEncoder;
   @Autowired
    private AuthenticationManager authenticationManager;
    private final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"); // OWASP

    @GetMapping("/user/login")
    public String login(Principal principal) {
        if (principal != null) return "redirect:/";
        return "user/login";
    }
    @GetMapping("/user/logout")
    public String logout(Principal principal) {
        if (principal == null) return "redirect:/";
        return "user/logout";
    }
    @GetMapping({"/user/register"})
    public String register(Principal principal) {
        if(principal != null) return "redirect:/";
        return "user/register";
    }

    @PostMapping({"/user/register"})
    public String registerPost(
            @RequestParam String username,
            @RequestParam String password,) {
        User newUser = new User();
        newUser.setUsername(username);
        String hashedPassword = passwordEncoder.encode(password);
        newUser.setPassword(hashedPassword);
        newUser.setRole("USER");
        userRepository.save(newUser);
        autologin(username, password);
        return "redirect:/user/register/confirmation";
    }
    @GetMapping({"/user/register/confirmation"})
    public String confirmationRegistration() {
        return "user/registration_confirmation";
    }
    private void autologin(String username, String password) {
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }
}
