package ejercicio;

import java.util.List;

class Cliente {
    private int id;
    private List<Integer> transacciones;

    public Cliente(int id, List<Integer> transacciones) {
        this.id = id;
        this.transacciones = transacciones;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getTransacciones() {
        return transacciones;
    }
}
