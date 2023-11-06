package actividad2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import java.io.*;

public class Principal {

    public static void main(String[] args) {
    	
        if (args.length < 6) {
            System.out.println("Debe proporcionar 6 nombres de alumnos para los dos exámenes.");
            return;
        }

        String[] examen1Alumnos = {args[0], args[1], args[2]};
        String[] examen2Alumnos = {args[3], args[4], args[5]};

        BufferExamenes generador = new BufferExamenes();

        Examinador[] examinadoresExamen1 = new Examinador[3];
        for (int i = 0; i < 3; i++) {
            examinadoresExamen1[i] = new Examinador(examen1Alumnos[i], generador);
        }

        Examinador[] examinadoresExamen2 = new Examinador[3];
        for (int i = 0; i < 3; i++) {
            examinadoresExamen2[i] = new Examinador(examen2Alumnos[i], generador);
        }

        // Iniciar los hilos de los examinadores
        for (Examinador examinador : examinadoresExamen1) {
            examinador.getHilo().start();
        }

        for (Examinador examinador : examinadoresExamen2) {
            examinador.getHilo().start();
        }

        // Esperar a que todos los hilos de los examinadores terminen
        try {
            for (Examinador examinador : examinadoresExamen1) {
                examinador.getHilo().join();
            }

            for (Examinador examinador : examinadoresExamen2) {
                examinador.getHilo().join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Redirigir la salida estándar a los archivos examen1.txt y examen2.txt
        try {
            PrintStream examen1Output = new PrintStream(new FileOutputStream("examen1.txt"));
            System.setOut(examen1Output);

            // Ejecutar el examen 1
            for (Examinador examinador : examinadoresExamen1) {
                System.out.println("Examen de " + examinador.getAlumno());
                for (int i = 1; i <= 10; i++) {
                    String respuesta = examinador.generarRespuestaAleatoria();
                    System.out.println("Pregunta " + i + ": " + respuesta);
                }
                System.out.println();
            }

            // Cerrar el archivo de salida del examen 1
            examen1Output.close();

            PrintStream examen2Output = new PrintStream(new FileOutputStream("examen2.txt"));
            System.setOut(examen2Output);

            // Ejecutar el examen 2
            for (Examinador examinador : examinadoresExamen2) {
                System.out.println("Examen de " + examinador.getAlumno());
                for (int i = 1; i <= 10; i++) {
                    String respuesta = examinador.generarRespuestaAleatoria();
                    System.out.println("Pregunta " + i + ": " + respuesta);
                }
                System.out.println();
            }

            // Cerrar el archivo de salida del examen 2
            examen2Output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

