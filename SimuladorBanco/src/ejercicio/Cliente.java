package ejercicio;

import java.util.List;

class Cliente {
	
	//atributos
    private int id;
    
    private List<Integer> transacciones;

    
    //constructor
    public Cliente(int id, List<Integer> transacciones) {
    	
        this.id = id;
        
        this.transacciones = transacciones;
        
    }

    
    //getter de Id
    public int getId() {
    	
        return id;
        
    }

    //getter de las transacciones
    public List<Integer> getTransacciones() {
    	
        return transacciones;
        
    }
}
