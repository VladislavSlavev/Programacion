package ejercicio;
import java.sql.ResultSet;

public class Usuario {
    private String nombre;
    private String email;
    private String telefono;
    private String fechaAlta;
    private int id;
    private Conexion conexion= new Conexion();
    
    public Usuario(String nombre, String email, String telefono, String fechaAlta, int id) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.fechaAlta = fechaAlta;
        this.id = id;
    }
    
    public Usuario(String nombre, String email, String telefono, String fechaAlta) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.fechaAlta = fechaAlta;
    }
    
    public Usuario() {
    	
    }

    //Almacenamiento de los datos del usuario
    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
    	
    	return telefono;
    	
    }
    
    public String getFechaAlta() {
    	
        return fechaAlta;
        
    }
    
    public int getId(){
    	
    	return id;
    	
    }
    
    public void insertarUsuario() {
    	
    	//Metodo para insertar el usuario en la base de datos
    	String sql = "INSERT INTO clientes (id, nombre, email, telefono, fecha_alta) VALUES (NULL, '"+nombre+"', '"+email+"', '"+telefono+"', '"+fechaAlta+"');";
    	conexion.executeUpdate(sql);
    	
    }
    
    public void eliminarUsuario(int id) {
    	
    	//Metodo para eliminar a un usuario
    	String sql = "DELETE FROM clientes WHERE id = '"+id+"';";
    	conexion.executeUpdate(sql);
    	
    }
    
    public void actualizarUsuario(int id, String nuevoNombre, String nuevoEmail, String nuevoTelefono, String nuevaFechaAlta) {
    	
    	//Metodo para editar un usuario
        String sql = "UPDATE clientes SET nombre = '" + nuevoNombre + "', email = '" + nuevoEmail + "', telefono = '" + nuevoTelefono + "', fecha_alta = '" + nuevaFechaAlta + "' WHERE id = " + id + ";";
        conexion.executeUpdate(sql);
    }

    
    //Metodo para mostrar los datos de todos los usuarios
    public ResultSet consultaBase() {
    	
    	String sql = "SELECT * FROM clientes;";
    	return conexion.executeQuery(sql);
    	
    }
}

