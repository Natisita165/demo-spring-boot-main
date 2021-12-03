package bo.edu.ucb.chatbot.dao;

import bo.edu.ucb.chatbot.dto.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmDao {

    private DataSource dataSource;
    private DataSource dataSource2;

    @Autowired
    public FilmDao(DataSource dataSource,DataSource dataSource2) {
        this.dataSource = dataSource;
        this.dataSource2 = dataSource2;
    }

    public List<Film> findByTitle(String title) {
        List<Film> result = new ArrayList<>();
        String query = "SELECT f.film_id, " +
                "   f.title, " +
                "   f.description, " +
                "   f.release_year, " +
                "   l.name as language , " +
                "   ol.name as original_language, " +
                "   f.rental_duration,"+
                "   f.rental_rate,"+
                "   f.length, " +
                "   f.replacement_cost,"+
                "   f.rating, " +
                "   f.special_features, " +
                "   f.last_update " +
                " FROM film f " +
                "     LEFT JOIN language l ON ( f.language_id = l.language_id) " +
                "     LEFT JOIN language ol ON ( f.original_language_id = ol.language_id) " +
                " WHERE " +
                "   UPPER(title) LIKE ( ? )" ;

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
                ) {
            System.out.println(query);
            pstmt.setString(1, "%"+title.toUpperCase()+ "%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Film film = new Film();
                film.setFilmId(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setReleaseYear(rs.getShort("release_year"));
                film.setLanguage("language");
                film.setOriginalLanguage("original_language");
                film.setRental_duration(rs.getInt("rental_duration"));
                film.setRental_rate(rs.getInt("rental_rate"));
                film.setLength(rs.getInt("length"));
                film.setReplacement_cost(rs.getInt("replacement_cost"));
                film.setRating(rs.getString("rating"));
                film.setSpecialFeatures(rs.getString("special_features"));
                java.sql.Date lastUpdate = rs.getDate("last_update");
                film.setLastUpdate(new java.util.Date(lastUpdate.getTime()));
                result.add(film);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return result;
    }





    public List<Film> findByActor(String actor) {
        List<Film> result = new ArrayList<>();
        String query = "SELECT f.film_id, " +
                "   a.actor_id,"+
                "   a.first_name,"+
                "   a.last_name,"+
                "   af.actor_id,"+
                "   af.film_id,"+
                "   f.title, " +
                "   f.description, " +
                "   f.release_year, " +
                "   l.name as language , " +
                "   ol.name as original_language, " +
                "   f.rental_duration,"+
                "   f.rental_rate,"+
                "   f.length, " +
                "   f.replacement_cost,"+
                "   f.rating, " +
                "   f.special_features, " +
                "   f.last_update " +
                " FROM film f " +
                "     LEFT JOIN language l ON ( f.language_id = l.language_id) " +
                "     LEFT JOIN language ol ON ( f.original_language_id = ol.language_id) " +
                "     LEFT JOIN film_actor af ON ( af.film_id = f.film_id) " +
                "     LEFT JOIN actor a ON ( a.actor_id = af.actor_id) " +
                " WHERE " +
                "   UPPER(a.first_name) LIKE ( ? ) or"+
                "   UPPER(a.last_name) LIKE ( ? )" ;

        try (
                Connection conn = dataSource2.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            System.out.println(query);
            String nombre[]=actor.split(" ",2);
            System.out.println(nombre[0]+"        "+nombre[1]);
            //pstmt.setString(0, "%"+nombre[0].toUpperCase()+ "%");
            pstmt.setString(1, "%"+nombre[0].toUpperCase()+ "%");
            pstmt.setString(2, "%"+nombre[1].toUpperCase()+ "%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Film film = new Film();
                film.setFilmId(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setReleaseYear(rs.getShort("release_year"));
                film.setLanguage("language");
                film.setOriginalLanguage("original_language");
                film.setRental_duration(rs.getInt("rental_duration"));
                film.setRental_rate(rs.getInt("rental_rate"));
                film.setLength(rs.getInt("length"));
                film.setReplacement_cost(rs.getInt("replacement_cost"));
                film.setRating(rs.getString("rating"));
                film.setSpecialFeatures(rs.getString("special_features"));
                java.sql.Date lastUpdate = rs.getDate("last_update");
                film.setLastUpdate(new java.util.Date(lastUpdate.getTime()));
                result.add(film);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return result;
    }

    public List<Film> findByTitleAndAutor(String autor, String titulo) {
        List<Film> result = new ArrayList<>();
        String query = "SELECT f.film_id, " +
                "   a.actor_id,"+
                "   a.first_name,"+
                "   a.last_name,"+
                "   af.actor_id,"+
                "   af.film_id,"+
                "   f.title, " +
                "   f.description, " +
                "   f.release_year, " +
                "   l.name as language , " +
                "   ol.name as original_language, " +
                "   f.rental_duration,"+
                "   f.rental_rate,"+
                "   f.length, " +
                "   f.replacement_cost,"+
                "   f.rating, " +
                "   f.special_features, " +
                "   f.last_update " +
                " FROM film f " +
                "     LEFT JOIN language l ON ( f.language_id = l.language_id) " +
                "     LEFT JOIN language ol ON ( f.original_language_id = ol.language_id) " +
                "     LEFT JOIN film_actor af ON ( af.film_id = f.film_id) " +
                "     LEFT JOIN actor a ON ( a.actor_id = af.actor_id) " +
                " WHERE " +
                "   (UPPER(a.first_name) LIKE ( ? ) or"+
                "   UPPER(a.last_name) LIKE ( ? ) )and" +
                "   UPPER(f.title) LIKE ( ? )" ;

        try (
                Connection conn = dataSource2.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            System.out.println(query);
            String nombre[]=autor.split(" ",2);
            System.out.println(nombre[0]+"        "+nombre[1]);
            //pstmt.setString(0, "%"+nombre[0].toUpperCase()+ "%");
            pstmt.setString(1, "%"+nombre[0].toUpperCase()+ "%");
            pstmt.setString(2, "%"+nombre[1].toUpperCase()+ "%");
            pstmt.setString(3, "%"+titulo.toUpperCase()+ "%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Film film = new Film();
                film.setFilmId(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setReleaseYear(rs.getShort("release_year"));
                film.setLanguage("language");
                film.setOriginalLanguage("original_language");
                film.setRental_duration(rs.getInt("rental_duration"));
                film.setRental_rate(rs.getInt("rental_rate"));
                film.setLength(rs.getInt("length"));
                film.setReplacement_cost(rs.getInt("replacement_cost"));
                film.setRating(rs.getString("rating"));
                film.setSpecialFeatures(rs.getString("special_features"));
                java.sql.Date lastUpdate = rs.getDate("last_update");
                film.setLastUpdate(new java.util.Date(lastUpdate.getTime()));
                result.add(film);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return result;
    }
    public List<Film> findByFilmsAll(String country) {
        List<Film> result = new ArrayList<>();
        String query = "SELECT f.film_id, " +
                "   a.actor_id,"+
                "   a.first_name,"+
                "   a.last_name,"+
                "   af.actor_id,"+
                "   af.film_id,"+
                "   f.title, " +
                "   f.description, " +
                "   f.release_year, " +
                "   l.name as language , " +
                "   ol.name as original_language, " +
                "   f.rental_duration,"+
                "   f.rental_rate,"+
                "   f.length, " +
                "   f.replacement_cost,"+
                "   f.rating, " +
                "   f.special_features, " +
                "   f.last_update, " +
                "   cat.name"+
                " FROM film_category fca, category cat, inventory i,store st, address ad, city ci, country co, film f " +
                "     LEFT JOIN language l ON ( f.language_id = l.language_id) " +
                "     LEFT JOIN language ol ON ( f.original_language_id = ol.language_id) " +
                "     LEFT JOIN film_actor af ON ( af.film_id = f.film_id) " +
                "     LEFT JOIN actor a ON ( a.actor_id = af.actor_id) " +
                "WHERE fca.category_id=cat.category_id AND f.film_id=fca.film_id AND f.language_id=l.language_id " +
                "                 AND af.film_id=f.film_id " +
                "                 AND a.actor_id=af.actor_id " +
                "                 AND co.country_id=ci.country_id " +
                "                 AND ci.city_id=ad.city_id " +
                "                 AND ad.address_id = st.address_id " +
                "                 AND i.store_id = st.store_id " +
                "                 AND f.film_id = i.film_id " +
                "                 AND co.country = ? " +
                "                 GROUP BY f.film_id";

        try (
                Connection conn = dataSource2.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            System.out.println(query);
            //pstmt.setString(0, "%"+nombre[0].toUpperCase()+ "%");
            pstmt.setString(1,country);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Film film = new Film();
                film.setFilmId(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setReleaseYear(rs.getShort("release_year"));
                film.setLanguage("language");
                film.setOriginalLanguage("original_language");
                film.setRental_duration(rs.getInt("rental_duration"));
                film.setRental_rate(rs.getInt("rental_rate"));
                film.setLength(rs.getInt("length"));
                film.setReplacement_cost(rs.getInt("replacement_cost"));
                film.setRating(rs.getString("rating"));
                film.setSpecialFeatures(rs.getString("special_features"));
                java.sql.Date lastUpdate = rs.getDate("last_update");
                film.setLastUpdate(new java.util.Date(lastUpdate.getTime()));
                film.setCategory(rs.getString("name"));
                result.add(film);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return result;
    }
}
