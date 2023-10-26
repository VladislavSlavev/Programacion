package hito;
import java.io.Serializable;

public class Movimiento implements Serializable {
	
	//atributos
    private String tipo;
    private double cantidad;

    
    //constructor
    public Movimiento(String tipo, double cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    //getters y setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    
    
    //metodo para transformar en String la lista de moviemientos 
    //ya que por defecto sale en un formato hexadecimalS
    @Override
    public String toString() {
        return "Tipo: " + tipo + ", Cantidad: " + cantidad;
    }
}

