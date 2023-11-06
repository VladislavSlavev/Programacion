package actividad2;

public class Principal {
    public static void main(String[] args) throws InterruptedException {
    	
        if (args.length < 1) {
            System.out.println("Debes proporcionar al menos un nombre de alumno como argumento.");
            return;
            
        }

        BufferExamenes generador = new BufferExamenes();

        // Verificamos si hay al menos dos argumentos para crear dos exámenes distintos.
        if (args.length >= 2) {
        	
            System.out.println("Creando dos exámenes distintos...");
            
            Thread primerExamen = new Thread(() -> {
            	
                for (int i = 0; i < args.length / 2; i++) {
                	
                    new ProductorExamenes(generador);
                    
                    
                }
            });

            Thread segundoExamen = new Thread(() -> {
            	
                for (int i = args.length / 2; i < args.length; i++) {
                	
                    new ProductorExamenes(generador);
                    
                }
            });

            primerExamen.start();
            segundoExamen.start();
            
        } else {
        	
            // Si solo hay un argumento, creamos un solo examen.
            System.out.println("Creando un examen...");
            for (String alumno : args) {
            	
                new ProductorExamenes(generador);
                
            }
        }

        // Creamos examinadores para los alumnos pasados como argumentos.
        for (String alumno : args) {
        	
            new Examinador(alumno, generador);
            
        }
    }
}
