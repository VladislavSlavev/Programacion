package ejercicio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import articulos.*;
public class Main {
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                menuElegir();
            }
        });
    }

    private static void menuElegir() {
        // Crear la ventana principal
        JFrame ventana = new JFrame("Selección de Tablas");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 100);
        ventana.setLocationRelativeTo(null);

        // Crear el panel que contendrá los botones
        JPanel panel = new JPanel();

        // Crear el botón "Tabla Usuarios"
        JButton botonUsuarios = new JButton("Tabla Usuarios");
        botonUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ejecutar el código de Main correspondiente a "Tabla Usuarios"
                VerTabla programa = new VerTabla();
                programa.setVisible(true);
            }
        });

        // Crear el botón "Tabla Artículos"
        JButton botonArticulos = new JButton("Tabla Artículos");
        botonArticulos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	articulos.VerTabla programa = new articulos.VerTabla();
                programa.setVisible(true);
            }
        });
        
        JButton botonFacturas = new JButton("Tabla Artículos");
        botonArticulos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	articulos.VerTabla programa = new articulos.VerTabla();
                programa.setVisible(true);
            }
        });

        // Agregar los botones al panel
        panel.add(botonUsuarios);
        panel.add(botonArticulos);
        panel.add(botonFacturas);

        // Agregar el panel a la ventana
        ventana.add(panel);

        // Mostrar la ventana
        ventana.setVisible(true);
    }
}

