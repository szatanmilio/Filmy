package com.example.filmy.web;

import com.example.filmy.web.dto.MovieStatusDto;
import com.example.filmy.web.dto.ProductionStatDto;
import com.example.filmy.web.dto.TVStatusDto;
import com.example.filmy.web.dto.UserDto;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.tv.TvSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
            List<String> status = myListController.getStatus(myMovies);
            List<MovieStatusDto> movieStatusDto = myListController.getMovieStatusDTO(myMovies, status);
            model.addAttribute("MyList", movieStatusDto);
            model.addAttribute("MyType", "MOVIE");
        }
        else if(type.equals("MOVIE")){
            List<MovieDb> myMovies = myListController.getMyMovies();
            List<String> status = myListController.getStatus(myMovies);
            List<MovieStatusDto> movieStatusDto = myListController.getMovieStatusDTO(myMovies, status);
            model.addAttribute("MyList", movieStatusDto);
            model.addAttribute("MyType", type);
        }
        else if(type.equals("TV")){
            List<TvSeries> myTvSeries = myListController.getMyTvSeries();
            List<String> status = myListController.getStatusTV(myTvSeries);
            List<TVStatusDto> tvStatusDto = myListController.getTVStatusDTO(myTvSeries, status);
            model.addAttribute("MyList", tvStatusDto);
            model.addAttribute("MyType", type);
        }
        return "myList";
    }

    @PostMapping("/myListStatus")
    public String myListStatus(Model model, String type, String status) {
        if(status.equals("ALL"))
            return myList(model, type);
        ProductionStatDto data = myListController.getAllData();
        model.addAttribute("data", data);
        if(type == null){
            List<MovieDb> myMovies = myListController.getMyMoviesByStatus(status);
            List<String> statusList = myListController.getStatus(myMovies);
            List<MovieStatusDto> movieStatusDto = myListController.getMovieStatusDTO(myMovies, statusList);
            model.addAttribute("MyList", movieStatusDto);
            model.addAttribute("MyType", type);
        }
        else if(type.equals("MOVIE")){
            List<MovieDb> myMovies = myListController.getMyMoviesByStatus(status);
            List<String> statusList = myListController.getStatus(myMovies);
            List<MovieStatusDto> movieStatusDto = myListController.getMovieStatusDTO(myMovies, statusList);
            model.addAttribute("MyList", movieStatusDto);
            model.addAttribute("MyType", type);
        }
        else if(type.equals("TV")){
            List<TvSeries> myTvSeries = myListController.getMyTvByStatus(status);
            List<String> statusList = myListController.getStatusTV(myTvSeries);
            List<TVStatusDto> tvStatusDto = myListController.getTVStatusDTO(myTvSeries, statusList);
            model.addAttribute("MyList", tvStatusDto);
            model.addAttribute("MyType", type);
        }
        return "myList";
    }


}
