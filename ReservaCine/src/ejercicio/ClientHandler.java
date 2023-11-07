package ejercicio;

import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class ClientHandler implements Runnable {
	
    private Socket clienteSocket;//socket de comunicacion con el cliente
    private DataInputStream input; //permite la lectura de datos
    private DataOutputStream output; //permite el envio de datos
    private Map<Integer, Boolean> asientos; //mapa que almacena el estado de los asientos
    private ReentrantLock lock; // objeto lock para garantizar la sincronizacion al manipular los asientos

    //constructor
    public ClientHandler(Socket socket, Map<Integer, Boolean> asientos, ReentrantLock lock) {
    	
        this.clienteSocket = socket;
        this.asientos = asientos;
        this.lock = lock;
        
    }

    @Override
    public void run() {
    	
        try {
        	
            input = new DataInputStream(clienteSocket.getInputStream()); //se abre un flujo de entrada desde el cliente
            output = new DataOutputStream(clienteSocket.getOutputStream()); //se abre un flujo de salida al cliente

            while (true) {
            	
            	//variable que almacena la opcion elegida
                int operacion = input.readInt();

                //si se elige la primera se llama al método que muestra todos los asientos
                if (operacion == 1) { 
                	
                    enviarAsientosDisponibles();
                    
                //si se elige la segunda se pide el numero del asiento para llamar al metodo de la reserver   
                } else if (operacion == 2) {
                	
                    int asiento = input.readInt();//se lee el numero
                    reservarAsiento(asiento);//llamada del metodo de reserva con el numero

                    //si el asiento esta libre se indica un mensaje de exito, si no, de fracaso
                    if (reservarAsiento(asiento)) {
                    	
                        output.writeInt(1);
                        
                    } else {
                    	
                        output.writeInt(0);
                        
                    }
                }
            }
            
        } catch (EOFException e) {
        	
            System.out.println("Cliente desconectado...");//control de desconeion
            
        } catch (IOException e) {
        	
            e.printStackTrace();//control de excepciones
            
        } finally {
        	
            try {
            	
                clienteSocket.close();//se cierra el socket de comunicacion con el cliente
                System.out.println("Cliente desconectado...");
                
            } catch (IOException e) {
            	
                e.printStackTrace();//control de excepciones al cerrar el socket
                
            }
        }
    }

    //metodo para enviar los asientos
    private void enviarAsientosDisponibles() throws IOException {
    	
    	//se recorre el mapa con los asientos
        for (Map.Entry<Integer, Boolean> entry : asientos.entrySet()) {
        	
            int numeroAsiento = entry.getKey();//la clave se guarda aqui
            
            boolean disponible = entry.getValue();//el valor se guarda aqui
            
            output.writeInt(numeroAsiento);//se envia el numero de asiento al cliente
            output.writeBoolean(disponible);//se envia el estado del asiento al cliente
            
        }
        
        output.writeInt(-1);//se marca el fin de la lista de asientos enviados al cliente
        
    }

    //metodo para reservar el asiento
    private boolean reservarAsiento(int asiento) {
    	
        lock.lock(); //se bloquea el acceso a la seccion critica utilizando el objeto de bloqueo

        try {
        	
        	//bucle el cual comprueba si el asiento elegido esta libre para poder reservarlo
            if (asientos.containsKey(asiento) && asientos.get(asiento)) {
            	
                asientos.put(asiento, false); //se marca el asiento como ocupado en el mapa de asientos
                return true; //la reserva se indica como completada
                
            }
            
        } finally {
        	
            lock.unlock(); //se libera el objeto de bloqueo
            
        }

        return false; //si el asiento esta ocupado la reserva se indica sin exito
        
    }
}
