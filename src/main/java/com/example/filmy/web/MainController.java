package com.example.filmy.web;

import com.example.filmy.web.dto.ProductionStatDto;
import com.example.filmy.web.dto.UserDto;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.tv.TvSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
    public String myList(Model model, String type) {
        ProductionStatDto data = myListController.getAllData();
        model.addAttribute("data", data);
        if(type == null){
            List<MovieDb> myMovies = myListController.getMyMovies();
            model.addAttribute("MyMovies", myMovies);
        }
        else if(type.equals("MOVIE")){
            List<MovieDb> myMovies = myListController.getMyMovies();
            model.addAttribute("MyMovies", myMovies);
        }
        else if(type.equals("TV")){
            List<TvSeries> myTvSeries = myListController.getMyTvSeries();
            model.addAttribute("MyMovies", myTvSeries);
        }
        return "myList";
    }

//    @GetMapping("/")
//    public String home() {
//        return "index";
//    }


}
