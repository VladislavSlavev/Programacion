package actividad1;

import java.util.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Queue;

public class BufferExamenes {
	
	//array que contendra la cola de examenes
	private Queue<String> colaExamenes;
	
	//metodo del buffer de la cola
	public BufferExamenes() {
		
		colaExamenes = new LinkedList<String>();
		
	}
	
	//metodo que hace los examenes
	public synchronized void fabricarNuevoExamen(String codigo) {
		
		// Aquí se fabrica un nuevo examen.
		// Un hilo de la clase ProductorExamenes
		// se encargará de fabricarlo y pasarlo como argumento a este método.
		
		colaExamenes.offer(codigo); //agregar el examen a la cola
		
		notify();//se notifica a los hilos que estan esperando un nuevo examen
	}
	
	public synchronized String consumirExamen() {
		
		// Este método se encargará de suministrar un examen
		// a cada hilo de tipo Examinador que lo solicite.
		
		// Para suministrar el examen, habrá que esperar
		// hasta que haya algún examen para consumir en la cola.
		
		
		//bucle que pausa el hilo hasta que haya examenes en la cola
		while (colaExamenes.isEmpty()) {
			
			try {
				
				wait();
				
			} catch (InterruptedException e) {
				
				Thread.currentThread().interrupt();
				
			}
		}
		
		//si la cola no esta vacia se saca un examen se entrega como return
		return colaExamenes.poll(); //se obtiene y se quita el examen de la cola
	}
}

