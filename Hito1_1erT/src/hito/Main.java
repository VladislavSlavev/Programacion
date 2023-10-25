package hito;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cuenta cuenta;
        File archivo = new File("cuenta.dat");

        //cargar la cuenta si existe, si no, crear una nueva
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                cuenta = (Cuenta) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar la cuenta: " + e.getMessage());
                return;
            }
        } else {
            Cliente cliente = new Cliente("Julián");
            cuenta = new Cuenta(cliente);
        }

        Scanner scanner = new Scanner(System.in);
        int opcion;

        while (true) {
        	
        	System.out.println("\nMenú:\n" +
        		    "1. Ingresar fondos\n" +
        		    "2. Retirar fondos\n" +
        		    "3. Ver saldo\n" +
        		    "4. Ver movimientos\n" +
        		    "5. Salir del programa\n" +
        		    "Elige una opción: ");

            try {
            	
                opcion = Integer.parseInt(scanner.nextLine());
                
            } catch (NumberFormatException e) {
            	
                System.out.println("Ingresa una opción válida.");
                continue;
                
            }

            switch (opcion) {
            
                case 1:
                	
                    // Ingresar fondos
                    System.out.println("Introduce la cantidad a ingresar:");
                    double cantidad = Double.parseDouble(scanner.nextLine());
                    cuenta.ingresarFondos(cantidad);
                    System.out.println("Se han ingresado " + cantidad + " euros, el fondo total es de " + cuenta.getSaldo() + " euros.");
                    break;
                    
                case 2:
                	
                    // Retirar fondos
                    System.out.println("Introduce la cantidad a retirar:");
                    double cantidadARetirar = Double.parseDouble(scanner.nextLine());
                    
                    if (cuenta.getSaldo() >= cantidadARetirar) {
                    	
                        cuenta.retirarFondos(cantidadARetirar);
                        System.out.println("Se han retirado " + cantidadARetirar + " euros, el fondo total es de " + cuenta.getSaldo() + " euros.");

                    } else {
                    	
                        System.out.println("Saldo insuficiente para realizar el retiro.");
                        
                    }
                    break;
                    
                case 3:
                	
                    // Ver saldo
                    System.out.println("Saldo actual de la cuenta: " + cuenta.getSaldo() + " euros.");
                    break;
                    
                case 4:
                	
                    // Ver movimientos
                    System.out.println("Movimientos de la cuenta:");
                    for (Movimiento movimiento : cuenta.getMovimientos()) {
                    	
                        System.out.println(movimiento);
                        
                    }
                    break;
                    
                case 5:
                	
                    // Salir del programa y guardar la cuenta
                    guardarCuenta(archivo, cuenta);
                    System.out.println("Saliendo del programa.");
                    return;
                    
                default:
                	
                    System.out.println("Opción no válida. Por favor, elige una opción válida.");
                    
            }
            
            System.out.print("\nPulsa Enter para continuar...");
            scanner.nextLine();
            
        }
    }

    private static void guardarCuenta(File archivo, Cuenta cuenta) {
    	
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
        	
            oos.writeObject(cuenta);
            
        } catch (IOException e) {
        	
            System.out.println("Error al guardar la cuenta: " + e.getMessage());
        }
    }
}
