package com.example.filmy.web;

import com.example.filmy.web.dto.MyListController;
import com.example.filmy.web.dto.ProductionStatDto;
import com.example.filmy.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    MyListController myListController;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        model.addAttribute("UserDto", new UserDto());
        return "settings";
    }

    @GetMapping("/myList")
    public String myList(Model model) {
        ProductionStatDto data = myListController.getAllData();
        model.addAttribute("data", data);
        return "myList";
    }

//    @GetMapping("/")
//    public String home() {
//        return "index";
//    }


}
