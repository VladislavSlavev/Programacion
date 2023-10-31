package ejercicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    
    public static void main(String[] args) {
        
        int numCajeros = 3; //numero de cajeros
        int numClientes = 10; //numero de clientes
        Random random = new Random();

        //ceacion clientes con transacciones aleatorias
        for (int i = 1; i <= numClientes; i++) {
            List<Integer> transacciones = new ArrayList<>();
            int numTransacciones = random.nextInt(4) + 1;//numero de transacciones aleatorias
            for (int j = 0; j < numTransacciones; j++) {
                transacciones.add(random.nextInt(500) + 100);//duracion de las transacciones
            }
            Cliente cliente = new Cliente(i, transacciones);
            clientes.add(cliente);
        }

        //creación de los cajeros y comienzo de la simulacion
        for (int i = 1; i <= numCajeros; i++) {
            Cajero cajero = new Cajero(i);
            Thread thread = new Thread(cajero);
            thread.start();
        }
    }
    
    //lista de clientes
    private static List<Cliente> clientes = new ArrayList<>();

    //metodo para verificar si hay clientes pendientes
    public static synchronized boolean hayClientesPendientes() {
        return !clientes.isEmpty();
    }

    //metodo para obtener el siguiente cliente de la cola
    public static synchronized Cliente siguienteCliente() {
        if (!clientes.isEmpty()) {
            Cliente cliente = clientes.remove(0);
            return cliente;
        }
        return null;
    }
}
