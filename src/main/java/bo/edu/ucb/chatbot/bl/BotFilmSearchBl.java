package bo.edu.ucb.chatbot.bl;

import bo.edu.ucb.chatbot.dto.Film;
import bo.edu.ucb.chatbot.exception.SakilaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Procesar la lógica de negocio de las conversaciones del bo.
 * Recibe los mensajes enviados por el usuario como String.
 * Y restorna una List<String> como respuesta a estos mensajes
 * Con el proposito de hacer High Cohesion esta clase no debería utilizar ningun API de Telegram
 */

@Component
public class BotFilmSearchBl {

    private FilmSearchBl filmSearchBl;
    private final String nomeFinal="Título";

    @Autowired
    public BotFilmSearchBl(FilmSearchBl filmSearchBl) {
        this.filmSearchBl = filmSearchBl;
    }

    public List<String> processMessage(String message) {
        String nome = identificador(message);
        String var=entrada(message);
        String var2=actores(message);
        String nombre="";
        String titulo="";
        System.out.println("Entrada"+message);
        List<String> result = new ArrayList<>();
        List<Film> filmList = new ArrayList<>();
        if(verificadorTituloActor(message)==2){
            nombre=verificadorActor(message);
            titulo=verificadorTitulo(message);
            filmList =  filmSearchBl.findByTitleAndAutor(nombre,titulo);
        }else{
            if(nome.equalsIgnoreCase(nomeFinal)){
                filmList =  filmSearchBl.findByTitle(var);
                System.out.println("Salida"+var);
            }else{
                filmList =  filmSearchBl.findByActor(var2);
                System.out.println("Salida2"+var);
            }
        }


        if (!filmList.isEmpty()) {
            result.add("Encontré las siguientes películas del:");
            for (Film film : filmList) {
                result.add(film.toString());
            }
        } else {
            result.add("No encontré ninguna película para: " + var);
        }

        return result;
    }

    private String identificador(String message) {
        String[] t1=message.split("=",2);
        return t1[0];
    }

    private String entrada(String message){
        String[] t1=message.split("=",2);
        return t1[1];
    }
    private String actores(String message){
        String[] t2=message.split("=",2);
        String[] t3=t2[1].split(" ",2);
        return t2[1];
    }
    public int verificadorTituloActor(String message){
        String[] t2=message.split("/",2);
        return t2.length;
    }
    public String verificadorTitulo(String message){
        String[] t2=message.split("/",2);
        String[] t3=t2[0].split("=",2);
        String[] t4=t2[1].split("=",2);
        if(t3[0].equals("Título")){
            return t3[1];
        }else{
            if(t4[0].equals("Título")){
                return t4[1];
            }else{
                throw new SakilaException(403, "Bad request: No se encontro el título");
            }
        }
    }
    public String verificadorActor(String message){
        String[] t2=message.split("/",2);
        String[] t3=t2[0].split("=",2);
        String[] t4=t2[1].split("=",2);
        System.out.println(t3[0]);
        System.out.println(t4[0]);
        if(t3[0].equals("Nombre")){
            return t3[1];
        }else{
            if(t4[0].equals("Nombre")){
                return t4[1];
            }else{
                throw new SakilaException(403, "Bad request: No se encontro el nombre");
            }
        }
    }
}
