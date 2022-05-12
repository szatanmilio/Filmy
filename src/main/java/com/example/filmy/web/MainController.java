package com.example.filmy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/settings")
    public String settings(){
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
