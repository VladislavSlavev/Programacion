package ejercicio;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.locks.*;

public class Server {
    private static final int PUERTO = 12365; //puerto en el que el servidor escucha las conexiones
    private static final int NUM_ASIENTOS = 10; //numero de asientos del cine
    private static Map<Integer, Boolean> asientos = new HashMap<>(); //mapa para representar si los asientos estan libres u ocupados
    private static ReentrantLock lock = new ReentrantLock(); // objeto lock para garantizar la sincronizacion al acceder 
    														 //y modificar el mapa de asientos desde multiples hilos

    public static void main(String[] args) {
    	
    	//en este for se inicializa el mapa de asientos y todos se marcan como true o disponibles al iniciar el servidor
        for (int i = 1; i <= NUM_ASIENTOS; i++) {
        	
            asientos.put(i, true);
            
        }

        //se crea un ServerSocket que escucha en el puerto especificado y espera conexiones entrantes
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
        	
            System.out.println("Servidor de Cine iniciado. Esperando conexiones en el puerto " + PUERTO + "...");
            
            while (true) {
            	
                Socket clienteSocket = serverSocket.accept(); //espera una conexion
                System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress()); //muestra la dirección IP del cliente
                new Thread(new ClientHandler(clienteSocket, asientos, lock)).start(); //se inicia un nuevo hilo para controlar la comunicacion con el cliente
                
            }
            
        } catch (IOException e) {
        	
            e.printStackTrace(); //manejo de excepciones
            
        }
    }
}


 