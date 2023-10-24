package hito;
import java.io.Serializable;

public class Movimiento implements Serializable {
    private String titulo;
    private String director;

    public Movimiento(String titulo, String director) {
        this.titulo = titulo;
        this.director = director;
    }

    // Getter para el título
    public String getTitulo() {
        return titulo;
    }

    // Setter para el título
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Getter para el director
    public String getDirector() {
        return director;
    }

    // Setter para el director
    public void setDirector(String director) {
        this.director = director;
    }
}

