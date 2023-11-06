package actividad1;

import java.util.Random;

public class Examinador implements Runnable {
	
	//atributos de la clase
	private Thread hilo;
	private BufferExamenes buffer;
	private String alumno;
	
	//getter de hilo
	public Thread getHilo() {
		
		return hilo;
		
	}
	
	//constructor
	public Examinador(String alumno, BufferExamenes generador) {
		
		this.alumno = alumno;
		this.buffer = generador;
		this.hilo = new Thread(this, alumno);
		this.hilo.start();
		
	}
	
	@Override
	public void run() {
		
		//el examinador recibe el código del examen 
		//que se va a revisar y lo almacena en la variable codigoExamen
		String codigoExamen = this.buffer.consumirExamen();
		
		//si codigo examen no esta vacio se cumple el siguiente bucle
		//si es el caso contrario aparece un mensaje notificando que no hay mas examenes
		if (codigoExamen != null) {

			
			//bucle que simula un examen de 10 preguntas
			for (int i = 1; i <= 10; i++) {
				
				//genera una respuesta aleatoria entre A, B, C, D o - llamando al metodo generarRespuestaAleatoria
				String respuesta = generarRespuestaAleatoria();
				
				//muestra el resultado del examen
				System.out.println(codigoExamen + "; " + alumno + "; Pregunta " + i + ";" + respuesta);
				
			}
			
		} else {
			
			System.out.println("Se ha agotado tiempo de espera y no hay más exámenes");//mensaje previamente mencionado
			
		}
	}
	
	//metodo para generar una respuesta aleatoria
	private String generarRespuestaAleatoria() {
		
		//array que contiene las posibles respuestas
		String[] opciones = {"A", "B", "C", "D", "-"};
		
		Random rand = new Random();
		
	    //genera un numero aleatorio entre 0 y la longitud del array opciones
		int randomIndex = rand.nextInt(opciones.length);
		
		return opciones[randomIndex];//devuelve una de las opciones aleatoriamente
		
	}
}
