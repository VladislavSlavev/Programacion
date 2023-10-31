package ejercicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

 public class Main {
    private static List<Cliente> clientes = new ArrayList<>();

    public static synchronized boolean hayClientesPendientes() {
        return !clientes.isEmpty();
    }

    public static synchronized Cliente siguienteCliente() {
        if (!clientes.isEmpty()) {
            Cliente cliente = clientes.remove(0);
            return cliente;
        }
        return null;
    }

    public static void main(String[] args) {
        int numCajeros = 3;
        int numClientes = 10;
        Random random = new Random();

        // Crear clientes con transacciones aleatorias
        for (int i = 1; i <= numClientes; i++) {
            List<Integer> transacciones = new ArrayList<>();
            int numTransacciones = random.nextInt(4) + 1;
            for (int j = 0; j < numTransacciones; j++) {
                transacciones.add(random.nextInt(500) + 100);
            }
            Cliente cliente = new Cliente(i, transacciones);
            clientes.add(cliente);
        }

        // Crear cajeros (hilos) y comenzar la simulación
        for (int i = 1; i <= numCajeros; i++) {
            Cajero cajero = new Cajero(i);
            Thread thread = new Thread(cajero);
            thread.start();
        }
    }
}