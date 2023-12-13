package Controller;

import Model.Film;
import Service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}
