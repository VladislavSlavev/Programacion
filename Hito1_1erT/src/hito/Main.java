package hito;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
    public static void main(String[] args) {
    	
    	//nueva instancia de cuenta y de file para abrir el archivo
        Cuenta cuenta;
        File archivo = new File("cuenta.dat");

        //carga la cuenta si existe si no se crea una nueva
        if (archivo.exists()) {
        	
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(archivo))) {
            	
                cuenta = (Cuenta) objectInputStream.readObject();
                
            } catch (IOException | ClassNotFoundException e) {
            	
                System.out.println("Error al cargar la cuenta: " + e.getMessage());
                
                return;
            }
            
        } else {
        	
        	//nueva instancia de cliente y de cuenta con el cliente
            Cliente cliente = new Cliente("Julián");
            cuenta = new Cuenta(cliente);
            
        }

        //nueva instancia del scanner
        Scanner scanner = new Scanner(System.in);
        
        //variable de las opciones del menu
        int opcion;

        //bucle principal del menu
        while (true) {
        	
        	System.out.println("\nMenú:\n" +
        		    "1. Ingresar fondos\n" +
        		    "2. Retirar fondos\n" +
        		    "3. Ver saldo\n" +
        		    "4. Ver movimientos\n" +
        		    "5. Salir del programa\n" +
        		    "Elige una opción: ");

        	//comprueba si se ha introducido un int, si no lo vuelve a pedir
            try {
            	
                opcion = Integer.parseInt(scanner.nextLine());
                
            } catch (NumberFormatException e) {
            	
                System.out.println("Ingresa una opción válida.");
                continue;
                
            }

            switch (opcion) {
            
            	//ingresar fondos
                case 1:
                	
                	//variable que ayuda a la logica del comprobamiento si se ha introducido un double
                	boolean inputDouble = false;
                	
                	//el bucle se realiza hasta que inputDouble sea true
                	do {
                		
                		try {
                			
                			//el programa pide un numero el cual se va a sumar a los fondos de la cuenta
                    		System.out.println("Introduce la cantidad a ingresar:");
                            double cantidad = Double.parseDouble(scanner.nextLine());
                            cuenta.ingresarFondos(cantidad);
                            //inputDouble se convierte en true para salir del bucle while
                            inputDouble = true;
                            System.out.println("Se han ingresado " + cantidad + " euros, el fondo total es de " + cuenta.getSaldo() + " euros.");
                            
						} catch (Exception e) {
							
							System.out.println("Debes ingresar un número...");//mensaje que te devuelve al paso anterior 
																			  //por si no se ha introducido un numero
							
						}
                		
					} while (!inputDouble);
                   
                    break;
                
                //retirar fondos
                case 2:
                	
                	//la logica aqui funciona igual que en el caso 1 solo cambia la accion
                	inputDouble = false;
                	
                	do {
						
                		try {
                			
                			//el programa pide una cantidad para retirar de los fondos
                            System.out.println("Introduce la cantidad a retirar:");
                            double cantidadARetirar = Double.parseDouble(scanner.nextLine());
                            
                            //condicional que comprueba si existen fondos suficientes para retirar
                            //si es asi los retira
                            if (cuenta.getSaldo() >= cantidadARetirar) {
                            	
                                cuenta.retirarFondos(cantidadARetirar);
                                inputDouble = true;
                                System.out.println("Se han retirado " + cantidadARetirar + " euros, el fondo total es de " + cuenta.getSaldo() + " euros.");

                            } else {
                            	
                                System.out.println("Saldo insuficiente para realizar el retiro.");//mensaje que te devuelve al paso anterior
                                																  //si no hay fondos suficientes
                                
                            }
                            
						} catch (Exception e) {
							
							System.out.println("Debes ingresar un número...");//mensaje que te devuelve al paso anterior 
							  												  //por si no se ha introducido un numero
						}
                		
					} while (!inputDouble);
                	
                    break;
               
                //comprobar el saldo de la cuenta
                case 3:
                	
                	//mensaje con llamada del metodo getSaldo
                    System.out.println("Saldo actual de la cuenta: " + cuenta.getSaldo() + " euros.");
                    break;
                    
                //ver los movimientos de la cuenta
                case 4:
                	
                    System.out.println("Movimientos de la cuenta:");
                    
                    //bucle que llama al metodo que recorre el array con los movimientos y los va mostrando
                    for (Movimiento movimiento : cuenta.getMovimientos()) {
                    	
                        System.out.println(movimiento);
                        
                    }
                    
                    break;
                 
                //salir del programa y guardar los cambios llamando al metodo guardarCuenta
                case 5:
                	
                    guardarCuenta(archivo, cuenta);
                    System.out.println("Saliendo del programa...");
                    return;
                  
                //si no se introduce ninguna de las opciones aparece este mensaje y te pide elegir de nuevo
                default:
                	
                    System.out.println("Opción no válida. Por favor, elige una opción válida.");
                    
            }
            
            System.out.print("\nPulsa Enter para continuar...");
            scanner.nextLine();
            
        }
    }

    //metodo para guardar los cambios
    private static void guardarCuenta(File archivo, Cuenta cuenta) {
    	
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(archivo))) {
        	
        	objectOutputStream.writeObject(cuenta);
            
        } catch (IOException e) {
        	
            System.out.println("Error al guardar la cuenta: \n" + e.getMessage());
        }
    }
}
