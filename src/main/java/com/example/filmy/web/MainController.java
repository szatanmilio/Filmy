package com.example.filmy.web;

import com.example.filmy.web.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

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
    public String myList(){
        return "myList";
    }

//    @GetMapping("/")
//    public String home() {
//        return "index";
//    }


}
