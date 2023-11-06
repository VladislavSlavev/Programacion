package facturas;
import java.sql.ResultSet;

public class Factura {
	
    private String fecha;
    private String total;
    private String cantidad;
    private String precio;
    private int id_factura;
    private int id_cliente;
    private int id_detalle;
    private int id_articulo;
    
    private Conexion conexion= new Conexion();
    
    public Factura(String fecha, String total, String cantidad, String precio, int id_factura, int id_cliente, int id_detalle, int id_articulo) {
    	
        this.fecha = fecha;
        this.total = total;
        this.cantidad = cantidad;
        this.precio = precio;
        this.id_factura = id_factura;
        this.id_cliente = id_cliente;
        this.id_detalle = id_detalle;
        this.id_articulo = id_articulo;
        
    }
    
    public Factura(String fecha, String total, String cantidad, String precio) {
    	
        this.fecha = fecha;
        this.total = total;
        this.cantidad = cantidad;
        this.precio = precio;

    }
    
    public Factura() {
    	
    }

    //Almacenamiento de los datos de la factura
    public String getFecha() {
    	
        return fecha;
    }

    public String getTotal() {
    	
        return total;
    }

    public String getCantidad() {
    	
    	return cantidad;
    	
    }
    
    public int getIdFactura() {
    	
        return id_factura;
        
    }
    
    public int getIdCliente() {
    	
    	return id_cliente;
    	
    }
    
    public int getIdDetalle(){
    	
    	return id_detalle;
    	
    }
    
    public int getIdArticulo(){
    	
    	return id_articulo;
    	
    }
    
    public void insertarFactura() {
    	
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

