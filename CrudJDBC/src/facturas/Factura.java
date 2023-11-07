package facturas;
import java.sql.ResultSet;

public class Factura {
	
    private String fecha;
    private String total;
    private String cantidad;
    private float precio;
    private int id_factura;
    private int id_cliente;
    private int id_detalle;
    private int id_articulo;
    
    private Conexion conexion= new Conexion();
    
    public Factura(int id_factura, int id_cliente, String fecha, String total, int id_detalle,  int id_articulo, String cantidad, float precio) {
    	
        this.fecha = fecha;
        this.total = total;
        this.cantidad = cantidad;
        this.precio = precio;
        this.id_factura = id_factura;
        this.id_cliente = id_cliente;
        this.id_detalle = id_detalle;
        this.id_articulo = id_articulo;
        
    }
    
    public Factura(int id_cliente, String fecha, String total, int id_articulo, String cantidad, float precio) {
    	
        this.id_articulo = id_articulo;
        this.id_articulo = id_articulo;
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
    	String sql = "INSERT INTO facturas (id_factura, id_cliente, fecha, total) VALUES (NULL, '"+id_factura+"', '"+id_cliente+"', '"+fecha+"', '"+total+"');";
    	conexion.executeUpdate(sql);
    	String sql2 = "INSERT INTO detallesfactura (id_detalle, id_factura, id_articulo, cantidad, precio) VALUES (NULL, '"+id_factura+"', '"+id_articulo+"', '"+cantidad+"', '"+precio+"');";
    	conexion.executeUpdate(sql2);
    	
    }
    
    public void eliminarFactura(int id) {
    	
    	//Metodo para eliminar a un usuario
    	String sql = "DELETE FROM facturas WHERE id_factura = '"+id_factura+"';";
    	conexion.executeUpdate(sql);
    	String sql2 = "DELETE FROM detallesfactura WHERE id_factura = '"+id_factura+"';";
    	conexion.executeUpdate(sql2);
    	
    	
    }
    
    public void actualizarFactura(int id_factura, int id_cliente, String fecha, String total, int id_articulo, String cantidad, float precio) {
    	
    	//Metodo para editar un usuario
        String sql = "UPDATE facturas SET id_cliente = '" + id_cliente + "', fecha = '" + fecha + "', total = '" + total + "' WHERE id_factura = " + id_factura + ";";
        conexion.executeUpdate(sql);
        String sql2 = "UPDATE detallesfactura SET id_articulo = '" + id_articulo + "', cantidad = '" + cantidad + "', precio = '" + precio + "' WHERE id_factura = " + id_factura + ";";
        conexion.executeUpdate(sql2);
        
    }

    
    //Metodo para mostrar los datos de todos los usuarios
    public ResultSet consultaBase() {
    	
    	String sql = "SELECT f.id_factura, f.id_cliente, f.fecha, f.total, df.id_articulo, df.cantidad, df.precio FROM facturas f INNER JOIN detallesfactura df ON f.id_factura = df.id_factura;";
    	return conexion.executeQuery(sql);
    	
    }
}

