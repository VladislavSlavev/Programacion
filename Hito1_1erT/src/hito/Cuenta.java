package hito;

import java.io.Serializable;
import java.util.ArrayList;

public class Cuenta implements Serializable {
    private Cliente cliente;
    private float movimientos;

    public Cuenta(Cliente cliente) {
        this.cliente = cliente;
        this.movimientos;
    }

    public void añadirPelicula(Movimiento movimiento) {
    	
        movimientos.add(movimiento);
        
    }

    public void eliminarPelicula(Pelicula pelicula) {
        peliculas.remove(pelicula);
    }

    // Getter para el operador
    public Operador getOperador() {
        return operador;
    }

    // Setter para el operador
    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    // Getter para la lista de películas
    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    // Setter para la lista de películas
    public void setPeliculas(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
}

