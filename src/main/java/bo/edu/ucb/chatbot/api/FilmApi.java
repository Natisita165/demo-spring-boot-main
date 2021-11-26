package bo.edu.ucb.chatbot.api;

import bo.edu.ucb.chatbot.bl.FilmSearchBl;
import bo.edu.ucb.chatbot.dto.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Bajo la perspectiva de High Cohesion. El API rest debería validar que lo que el cliente envía, entenediendo por
 * cliente a las aplicaciones Web y Móvil, son datos correctos..
 *
 * Por ejemplo para la busqueda por titulo el API rest no debería pasarle un titulo nulo.
 */

@RestController
@RequestMapping("/v1/api/film")
public class FilmApi {

    FilmSearchBl filmSearchBl;

    @Autowired
    public FilmApi(FilmSearchBl filmSearchBl) {
        this.filmSearchBl = filmSearchBl;
    }

    @GetMapping(value = "/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> findBytTitle(@PathVariable(name = "title") String title) {
        System.out.println("Invocando al metodo GET!!!!!!!!!!!");
        return filmSearchBl.findByTitle(title);
    }

    @GetMapping(value = "/{first_name last_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> findBytActor(@PathVariable(name = "first_name last_name") String actor) {
        System.out.println("Invocando al metodo GET!!!!!!!!!!!");
        return filmSearchBl.findByActor(actor);
    }
    @RequestMapping(path = "/getFilm",method = RequestMethod.GET)
    public List<Film> getFilms(@RequestParam String title, @RequestParam String actor){
        List<Film> films = filmSearchBl.getFilms(title, actor);
        return films;
    }
    @RequestMapping(path = "/getFilmAll",method = RequestMethod.GET)
    public List<Film> getFilmsAll(@RequestParam Integer page, @RequestParam Integer size){
        List<Film> films = filmSearchBl.getFilmsAll(page, size);
        return films;
    }
}
