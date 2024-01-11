package com.externalapi.Service;

import com.externalapi.Model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class FilmService {

    @Autowired
    private RestTemplate template = new RestTemplate();

    Film[] filmArray = template.getForObject("https://ghibliapi.vercel.app/films/", Film[].class);

    public Object[] findAllFilmsComplete () {
        return template.getForObject("https://ghibliapi.vercel.app/films/", Object[].class);
    }

    public Film[] findAllFilms () {
        return template.getForObject("https://ghibliapi.vercel.app/films/", Film[].class);
    }

    public List<Film> FindFilmsByTitle(String title) {
        List<Film> filmList = new ArrayList<>();
        Film[] forObject = template.getForObject("https://ghibliapi.vercel.app/films/", Film[].class);
        for (int i = 0; i < forObject.length; i++) {
            Film film = forObject[i];
            String englishTitle = film.getTitle().toLowerCase();
            String romanTitle = film.getOriginal_title_romanised().toLowerCase();
            if (englishTitle.contains(title.toLowerCase()) || romanTitle.contains(title.toLowerCase())) {
                filmList.add(film);
            }
        }
        return filmList;
    }

    public Map<Integer, String> findDirectors() {
        Map<Integer, String> map = new HashMap<>();
        List<Film> filmList = Arrays.asList(filmArray);
        int mapKey = 1;
        for (Film film : filmList) {
            String director = film.getDirector();
            if (!map.containsValue(director)) {
                map.put(mapKey,director);
                mapKey++;
            }
        }
        return map;
    }

    public List<Film> findFilmsByTheNewest () {
        List<Film> filmList = Arrays.asList(filmArray);
        sortFilmsByReleaseDate(filmList);
        return filmList;
    }

    private List<Film> sortFilmsByReleaseDate (List<Film> list) {
        list.sort(Comparator.comparing(film -> film.getRelease_date()));
        Collections.reverse(list);
        return list;
    }
}
