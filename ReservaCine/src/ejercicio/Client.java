package ejercicio;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String SERVER_IP = "localhost";//IP del servidor al que se hara la conexion
    private static final int SERVER_PORT = 12365;//puerto del servidor 

    public static void main(String[] args) {
    	
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT); //se crea un socket para conectarse al servidor
             DataInputStream input = new DataInputStream(socket.getInputStream());//permite la lectura de datos del servidor
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {//permite el envio de datos al servidor

            Scanner scanner = new Scanner(System.in);//nuevo scanner

            while (true) {
            	
            	//menu de opciones
                System.out.println("\n1. Ver asientos disponibles"
                        + "\n2. Reservar asiento"
                        + "\n3. Salir"
                        + "\nSelecciona una opción: ");

                int opcion = Integer.parseInt(scanner.nextLine());//guarda la opcion elegida

                //si la opcion es 1 se llama el metodo de mostrar los asientos
                if (opcion == 1) {
                	
                    output.writeInt(1);
                    recibirAsientosDisponibles(input);
                    
                //si la opcion es 2 se pide elegir un numero de asiento a reservar    
                } else if (opcion == 2) {
                	
                    System.out.print("Ingresa el número de asiento a reservar: ");
                    
                    int asiento = Integer.parseInt(scanner.nextLine()); //se lee el numero de asiento ingresado por el usuario
                    
                    output.writeInt(2); //se envia al servidor la operacion de reservar asiento
                    
                    output.writeInt(asiento); //se envia el numero de asiento
                    
                    int respuesta = input.readInt(); //se lee la respuesta del servidor

                    //si la respuesta es uno se infroma que el asiento se ha reservado si no es que está ocupado o no existe
                    if (respuesta == 1) {
                    	
                        System.out.println("Asiento reservado con éxito");
                        
                    } else {
                    	
                        System.out.println("El asiento está ocupado o no existe");
                        
                    }
                    
                //si la opcion es 3 se sale del programa    
                } else if (opcion == 3) {
                	
                    System.out.println("Desconectando...");
                    break;
                    
                }
            }

        } catch (IOException e) {
        	
            e.printStackTrace();//manejo de excepciones
            
        }
    }

    //metodo para recibir los asientos
    private static void recibirAsientosDisponibles(DataInputStream input) throws IOException {
    	
        while (true) {
        	
            int numeroAsiento = input.readInt(); //se lee el numero de asiento enviado por el servidor
            if (numeroAsiento == -1) {
            	
                break; //si el servidor envia -1 marca el fin de la lista de asientos
                
            }
            
            boolean disponible = input.readBoolean(); //lee el estado del asiento desde el servidor
            System.out.println(numeroAsiento + ": " + (disponible ? "Disponible" : "Ocupado")); //muestra el estado del asiento
            
        }
    }
}
