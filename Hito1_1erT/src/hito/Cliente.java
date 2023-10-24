package hito;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String nombre;

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    // Getter para el nombre
    public String getNombre() {
        return nombre;
    }

    // Setter para el nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
