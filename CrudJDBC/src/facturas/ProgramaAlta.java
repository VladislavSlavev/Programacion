package facturas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProgramaAlta extends JFrame {
    private List<Factura> articulos;
    private JTextField txtNombre;
    private JTextField txtIdCat;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JTextField txtFechaIngreso;


    public ProgramaAlta() {
        articulos = new ArrayList<>();
        
        // Creacion y configuración de la ventana
        setTitle("Programa de Alta");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creación de los componentes de la interfaz
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField("", 20);

        JLabel lblIdCat = new JLabel("ID de Categoria:");
        txtIdCat = new JTextField(10);

        JLabel lblPrecio = new JLabel("Precio:");
        txtPrecio = new JTextField(10);
        
        JLabel lblStock = new JLabel("Stock:");
        txtStock = new JTextField(10);

        JLabel lblFechaIngreso = new JLabel("Fecha de Ingreso(YYYY/MM/DD):");
        txtFechaIngreso= new JTextField(10);

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
        panelPrincipal.add(lblIdCat, gbc);

        gbc.gridx = 1;
        panelPrincipal.add(txtIdCat, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPrincipal.add(lblPrecio, gbc);

        gbc.gridx = 1;
        panelPrincipal.add(txtPrecio, gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        panelPrincipal.add(lblStock, gbc);

        gbc.gridx = 1;
        panelPrincipal.add(txtStock, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        panelPrincipal.add(lblFechaIngreso, gbc);
        
        gbc.gridx = 1;
        panelPrincipal.add(txtFechaIngreso, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(btnCalcular, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
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
        String idCat = txtIdCat .getText();
        String precio = txtPrecio .getText();
        String stock = txtStock.getText();
        String fechaIngreso = txtFechaIngreso.getText();

        
        //Controles de error
        if (nombre.isEmpty()) {
        	mostrarError("Debes introducir un nombre");
        	return;
        }
        
        if (idCat.isEmpty()) {
        	mostrarError("Debes introducir un numero de categoria");
        	return;
        }
        
        if (precio.isEmpty()) {
        	mostrarError("Debes introducir un precio");
        	return;
        }
        
        if (stock.isEmpty()) {
        	mostrarError("Debes introducir un numero de stock");
        	return;
        }
        
        if (fechaIngreso.isEmpty()) {
        	mostrarError("Debes introducir una fecha de ingreso");
        	return;
        }
        
        //Creacion de nueva instancia
        Factura articulo = new Factura(nombre, idCat, precio, stock, fechaIngreso);
        articulos.add(articulo);

        mostrarInformacion(articulo);
    }

    private void mostrarInformacion(Factura articulo) {
    	
        String resultado = "Nombre: " + articulo.getNombre() + "\n\n" +
                "ID de Categoria: " + articulo.getIdCat() + "\n\n" +
                "Precio: " + articulo.getPrecio() + "\n\n" +
                "Stock: " + articulo.getStock() + "\n\n" +
                "Fecha de Ingreso: " + articulo.getFechaIngreso() + "\n\n";

        JOptionPane.showMessageDialog(this, resultado, "Resultados", JOptionPane.INFORMATION_MESSAGE);
        
        //Llamada del metodo para insertar el usuario en la base de datos
        articulo.insertarArticulo();
    }
    
    //Mensaje de error
    private void mostrarError(String mensaje) {
    	JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}


