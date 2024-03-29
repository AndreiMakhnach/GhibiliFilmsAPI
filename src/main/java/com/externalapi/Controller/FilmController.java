package com.externalapi.Controller;

import com.externalapi.Model.Film;
import com.externalapi.Service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping("/complete")
    public Object[] getAllFilmsComplete() {
            return filmService.findAllFilmsComplete();
    }

    @GetMapping
    public Film[] getAllFilms() {
        return filmService.findAllFilms();
    }

    @GetMapping("/title")
    public List<Film> getFilmsByTitle(@RequestParam("q") String title) {
        return filmService.FindFilmsByTitle(title);
    }

    @GetMapping("/directors")
    public Map<Integer, String> getDirectors() {
        return filmService.findDirectors();
    }

    public List<Film> getTheNewestFilms() {
        return filmService.findFilmsByTheNewest();
    }
}
