package com.example.mvc.controllers;

import com.example.mvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class MainController {
    private List<User> users = new ArrayList<>();


    public MainController() {
        users.add(new User(2, "kokos"));
        users.add(new User(1, "ananas"));
        users.add(new User(3, "banan"));
        users.add(new User(5, "tomat"));
        users.add(new User(4, "potatos"));
        users.add(new User(6, "mango"));
    }

    @GetMapping("/")
    public String home(Model model) {

        HashMap<String, String> map = new HashMap<>();
        map.put("msg", "hello okten");
        map.put("description", "Lorem ipsum dolor.");


        model.addAllAttributes(map);
        return "index.html";
    }

    @GetMapping("/users")
    public String usersPage(Model model) {
        model.addAttribute("users", users);
        return "userspage.html";
    }


    @GetMapping("/user")
    public String userPage(@RequestParam("id") int id, Model model) {
        model.addAttribute("id", id);

        return "user.html";
    }


    @GetMapping("/user/{id}")
    public String getUserInfo(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        return "user.html";
    }

    @PostMapping("/user")
    public String saveUser(@RequestParam int id, @RequestParam String name/*, RedirectAttributes redirectAttributes*/) {

        users.add(new User(id, name));
//        model.addAttribute("users", users); // duplicate of     @GetMapping("/users")
//        redirectAttributes.addFlashAttribute("users", users);

        return "redirect:/users";
    }


}
