package actividad2;

public class ProductorExamenes implements Runnable {
	
	//atributos
	private BufferExamenes buffer;
	private static int numeroExamen = 0;
	private Thread hilo;
	
	//metodo que produce los examenes
	public ProductorExamenes(BufferExamenes buffer) {
		
		//incrementa el contador de examenes
		numeroExamen++;
		
		//construye el hilo, el nombre será la letra E seguida
		//del valor de la variable numeroExamen
		this.hilo = new Thread(this, "E" + numeroExamen);
		
		//asigna el valor de la propiedad buffer
		this.buffer = buffer;
		
		//inicio del hilo
		this.hilo.start();
	}
	
	@Override
	public void run() {
		
		//se pausa el hilo medio segundo para que ayude a que
		//el momento en el que se generen los mensajes sean mas aleatorios
	    try {
	    	
	        Thread.sleep(500);
	        
	    } catch (InterruptedException e) {
	    	
	        Thread.currentThread().interrupt();
	        
	    }
		
		//generacion del codigo del examen
		String codigo = this.hilo.getName() + "-2018";
		
		//se añade el nuevo codigo al buffer de examenes
		buffer.fabricarNuevoExamen(codigo);
		
		//mensaje indicando que se ha producido el nuevo examen y su codi
		System.out.println("Nuevo examen producido: " + codigo);
		
	}
}
