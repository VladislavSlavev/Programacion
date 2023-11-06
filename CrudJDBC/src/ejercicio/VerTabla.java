package ejercicio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;

public class VerTabla extends JFrame {
    private JTable tabla;
    private JScrollPane scrollPane;
    private Usuario usuario = new Usuario();
    private JButton botonCargarDatos;
    private JButton botonActualizarDatos;
    
    public VerTabla() {
    	
        // Creacion de la ventana para visualizar la tabla
    	
        setTitle("Tabla");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        tabla = new JTable();
        scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);

        // Obtener los datos de la base de datos
        obtenerDatos();
        
        botonActualizarDatos = new JButton("Actualizar Tabla");
        botonActualizarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obtenerDatos(); // Llama al método obtenerDatos cuando se pulsa el botón "Actualizar"
            }
        });
        add(botonActualizarDatos, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());

        // Creacion del boton para agregar un usuario
        botonCargarDatos = new JButton("Añadir Usuario");
        botonCargarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ProgramaAlta programa = new ProgramaAlta();
                
                programa.setVisible(true);
            }
        });
        buttonPanel.add(botonCargarDatos, BorderLayout.CENTER);
        
        JButton botonEditarUsuario = new JButton("Editar Usuario");
        botonEditarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Muestra un cuadro de diálogo para ingresar el número de usuario a eliminar
               String usuarioAEditar = JOptionPane.showInputDialog("Ingrese el número de usuario a editar:");
                if (usuarioAEditar != null && !usuarioAEditar.isEmpty()) {
                    try {
                        int numeroUsuario = Integer.parseInt(usuarioAEditar);
                        
                        // Mostrar un segundo cuadro de diálogo para ingresar los nuevos datos
                        String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:");
                        String nuevoEmail = JOptionPane.showInputDialog("Nuevo email:");
                        String nuevoTelefono = JOptionPane.showInputDialog("Nuevo teléfono:");
                        String nuevaFechaAlta = JOptionPane.showInputDialog("Nueva fecha de alta:");
                        
                        // Llama al método de Usuario para actualizar el usuario
                        usuario.actualizarUsuario(numeroUsuario, nuevoNombre, nuevoEmail, nuevoTelefono, nuevaFechaAlta);
                        
                        // Actualiza la tabla después de editar
                        obtenerDatos();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Ingrese un número válido de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
        
        buttonPanel.add(botonEditarUsuario, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
        
        //---------------------------------------------------------
        
        JButton botonEliminarUsuario = new JButton("Eliminar Usuario");
        botonEliminarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Muestra un cuadro de diálogo para ingresar el número de usuario a eliminar
                String usuarioAEliminar = JOptionPane.showInputDialog("Ingrese el número de usuario a eliminar:");
                
                if (usuarioAEliminar != null && !usuarioAEliminar.isEmpty()) {
                    try {
                        int numeroUsuario = Integer.parseInt(usuarioAEliminar);
                        // Llama al método de Usuario para eliminar el usuario por su número
                        usuario.eliminarUsuario(numeroUsuario);

                        // Actualiza la tabla después de eliminar
                        obtenerDatos();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Ingrese un número válido de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        buttonPanel.add(botonEliminarUsuario, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);
        
        

        //--------------------------------------------------------------
        //Mostrar la ventana
        setVisible(true);

    }

    private void obtenerDatos() {
        try {
        	//Creacion del metodo para mostrar la tabla de la base de datos
            ResultSet resultSet = usuario.consultaBase();

            // Crear un modelo de tabla para almacenar los datos
            DefaultTableModel modeloTabla = new DefaultTableModel();
            

            // Obtener datos de las columnas
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numeroColumnas = metaData.getColumnCount();

            // Agregar nombres de columnas
            for (int columna = 1; columna <= numeroColumnas; columna++) {
                modeloTabla.addColumn(metaData.getColumnName(columna));
            }

            // Agregar filas al modelo con los datos de la consulta
            while (resultSet.next()) {
                Object[] fila = new Object[numeroColumnas];
                for (int columna = 1; columna <= numeroColumnas; columna++) {
                    fila[columna - 1] = resultSet.getObject(columna);
                }
                modeloTabla.addRow(fila);
            }

            // Establecer el modelo de tabla
            tabla.setModel(modeloTabla);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


