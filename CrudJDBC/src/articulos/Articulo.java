package articulos;
import java.sql.ResultSet;

public class Articulo {
    private String nombre;
    private String id_cat;
    private String precio;
    private String stock;
    private String fecha_ingreso;
    private int id_art;
    private Conexion conexion= new Conexion();
    
    public Articulo(String nombre, String id_cat, String precio, String stock, String fecha_ingreso, int id_art) {
        this.nombre = nombre;
        this.id_cat = id_cat;
        this.precio = precio;
        this.stock = stock;
        this.fecha_ingreso = fecha_ingreso;
        this.id_art = id_art;
    }
    
    public Articulo(String nombre, String id_cat, String precio, String stock, String fecha_ingreso) {
        this.nombre = nombre;
        this.id_cat = id_cat;
        this.precio = precio;
        this.stock = stock;
        this.fecha_ingreso = fecha_ingreso;
    }
    
    public Articulo() {
    	
    }

    //Almacenamiento de los datos del usuario
    public String getNombre() {
        return nombre;
    }

    public String getIdCat() {
        return id_cat;
    }

    public String getPrecio() {
    	
    	return precio;
    	
    }
    
    public String getStock() {
    	
        return stock;
        
    }
    
    public String getFechaIngreso() {
    	
    	return fecha_ingreso;
    	
    }
    
    public int getIdArt(){
    	
    	return id_art;
    	
    }
    
    public void insertarArticulo() {
    	
    	//Metodo para insertar el usuario en la base de datos
    	String sql = "INSERT INTO articulos (id_articulo, nombre, id_categoria, precio, stock, fecha_ingreso) VALUES (NULL, '"+nombre+"', '"+id_cat+"', '"+precio+"', '"+stock+"', '"+fecha_ingreso+"');";
    	conexion.executeUpdate(sql);
    	
    }
    
    public void eliminarArticulo(int id) {
    	
    	//Metodo para eliminar a un usuario
    	String sql = "DELETE FROM articulos WHERE id = '"+id+"';";
    	conexion.executeUpdate(sql);
    	
    }
    
    public void actualizarArticulo(int id, String nuevoNombre, int nuevoIdCat, float nuevoPrecio, int nuevoStock, String nuevaFechaIngreso) {
    	
    	//Metodo para editar un usuario
        String sql = "UPDATE articulos SET nombre = '" + nuevoNombre + "', id_categoria = '" + nuevoIdCat + "', precio = '" + nuevoPrecio + "', stock = '" + nuevoStock + "', fecha_ingreso = '" + nuevaFechaIngreso + "' WHERE id = " + id + ";";
        conexion.executeUpdate(sql);
    }

    
    //Metodo para mostrar los datos de todos los usuarios
    public ResultSet consultaBase() {
    	
    	String sql = "SELECT * FROM articulos;";
    	return conexion.executeQuery(sql);
    	
    }
}

