package hito;

import java.io.Serializable;

//clase cliente con su atributo, constructor, setter y getter
public class Cliente implements Serializable {
    private String nombre;

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
