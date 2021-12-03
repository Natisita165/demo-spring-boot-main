package bo.edu.ucb.chatbot.bl;

import bo.edu.ucb.chatbot.dao.FilmDao;
import bo.edu.ucb.chatbot.dto.Film;
import bo.edu.ucb.chatbot.exception.SakilaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public class FilmSearchBl {

    private final FilmDao filmDao;

    @Autowired
    public FilmSearchBl(FilmDao filmDao) {
        this.filmDao = filmDao;
    }

    public List<Film> findByTitle(String title) {
        if (title == null || title.trim().equals("")) {
            throw new SakilaException(403, "Bad request: The title parameter is mandatory and can't be null or empty");
        }
        return filmDao.findByTitle(title);
    }
    public List<Film> findByActor(String actor) {
        if (actor == null || actor.trim().equals("")) {
            throw new SakilaException(403, "Bad request: The title parameter is mandatory and can't be null or empty");
        }
        return filmDao.findByActor(actor);
    }

    public List<Film> findByTitleAndAutor(String nombre, String titulo) {
        if ((nombre == null || nombre.trim().equals(""))&&(titulo == null || titulo.trim().equals(""))) {
            throw new SakilaException(403, "Bad request: The title parameter is mandatory and can't be null or empty");
        }
        return filmDao.findByTitleAndAutor(nombre,titulo);
    }
    public List<Film> getFilms(String title, String actor) {
        if ((actor == null || actor.trim().equals(""))&&(title == null || title.trim().equals(""))) {
            throw new SakilaException(403, "Bad request: The title parameter is mandatory and can't be null or empty");
        }
        return filmDao.findByTitleAndAutor(actor,title);
    }
    public List<Film> getFilmsAll(String country) {

        return filmDao.findByFilmsAll(country);
    }
}
