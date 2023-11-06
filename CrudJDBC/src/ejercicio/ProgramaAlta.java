package ejercicio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProgramaAlta extends JFrame {
    private List<Usuario> usuarios;
    private JTextField txtNombre;
    private JTextField txtEmail;
    private JTextField txtTlf;
    private JTextField txtFecha;


    public ProgramaAlta() {
        usuarios = new ArrayList<>();
        
        // Creacion y configuración de la ventana
        setTitle("Programa de Alta");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creación de los componentes de la interfaz
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField("", 20);

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField(10);

        JLabel lblTlf = new JLabel("Telefono:");
        txtTlf = new JTextField(10);

        JLabel lblFecha = new JLabel("Fecha(YYYY/MM/DD):");
        txtFecha= new JTextField(10);

        JButton btnCalcular = new JButton("Resultado");
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarUsuario();
            }
        });
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Configuración del panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Agregar componentes al panel principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelPrincipal.add(lblNombre, gbc);

        gbc.gridx = 1;
        panelPrincipal.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPrincipal.add(lblEmail, gbc);

        gbc.gridx = 1;
        panelPrincipal.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPrincipal.add(lblTlf, gbc);

        gbc.gridx = 1;
        panelPrincipal.add(txtTlf, gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        panelPrincipal.add(lblFecha, gbc);

        gbc.gridx = 1;
        panelPrincipal.add(txtFecha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(btnCalcular, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(btnCerrar, gbc);
        
        // Agregar panel principal a la ventana
        getContentPane().add(panelPrincipal);

        // Mostrar la ventana
        setVisible(true);
    }

    private void agregarUsuario() {
    	
    	//Metodo para añadir un usuario
        String nombre = txtNombre.getText();
        String email = txtEmail.getText();
        String telefono = txtTlf.getText();
        String fechaAlta = txtFecha.getText();

        
        //Controles de error
        if (nombre.isEmpty()) {
        	mostrarError("Debes introducir un nombre");
        	return;
        }
        
        if (email.isEmpty()) {
        	mostrarError("Debes introducir un email");
        	return;
        }
        
        if (telefono.isEmpty()) {
        	mostrarError("Debes introducir un telefono");
        	return;
        }
        
        if (fechaAlta.isEmpty()) {
        	mostrarError("Debes introducir una fecha");
        	return;
        }
        
        //Creacion de nueva instancia
        Usuario usuario = new Usuario(nombre, email, telefono, fechaAlta);
        usuarios.add(usuario);

        mostrarInformacion(usuario);
    }

    private void mostrarInformacion(Usuario usuario) {
    	
        String resultado = "Nombre: " + usuario.getNombre() + "\n\n" +
                "Email: " + usuario.getEmail() + "\n\n" +
                "Telefono: " + usuario.getTelefono() + "\n\n" +
                "Fecha de Alta: " + usuario.getFechaAlta() + "\n\n";

        JOptionPane.showMessageDialog(this, resultado, "Resultados", JOptionPane.INFORMATION_MESSAGE);
        
        //Llamada del metodo para insertar el usuario en la base de datos
        usuario.insertarUsuario();
    }
    
    //Mensaje de error
    private void mostrarError(String mensaje) {
    	JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}


