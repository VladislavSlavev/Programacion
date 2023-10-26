package hito;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cuenta implements Serializable {
	
	//atributos
    private Cliente cliente;
    private double saldo;
    private List<Movimiento> movimientos;

    //constructor
    public Cuenta(Cliente cliente) {
    	
        this.cliente = cliente;
        this.saldo = 0.0;
        this.movimientos = new ArrayList<>();
        
    }
    
    //getter del cliente
    public Cliente getCliente() {
    	
        return cliente;
        
    }

    
    //getter del saldo
    public double getSaldo() {
    	
        return saldo;
        
    }
    
    //getter de la lista de movimientos
    public List<Movimiento> getMovimientos() {
    	
        return movimientos;
        
    }
    
    //metodo para ingresar fondos en la cuenta
    public void ingresarFondos(double cantidad) {
    	
        if (cantidad > 0) {
        	
        	//se suma la cantidad elegida al saldo y despues se añade 
        	//la accion realizada a la lista de movimientos
            saldo += cantidad;
            Movimiento movimiento = new Movimiento("Ingreso", cantidad);
            movimientos.add(movimiento);
            
        }
    }

    //metodo para retirar fondos
    public void retirarFondos(double cantidad) {
    	
    	//comprueba si la cuenta tiene saldo y si se puede retirar 
    	//la cantidad indicada de dinero
        if (cantidad > 0 && saldo >= cantidad) {
        	
        	//se restan los fondos y se añade la accion a la lista de movimientos
            saldo -= cantidad;
            Movimiento movimiento = new Movimiento("Retiro", cantidad);
            movimientos.add(movimiento);
            
        }
    }

}