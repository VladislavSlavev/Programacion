package ejercicio;

class Cajero implements Runnable {
    
    private int id;

    //constructor
    public Cajero(int id) {
        
        this.id = id;
        
    }

    //metodo para atender a un cliente
    public void atender(Cliente cliente) {
        
        //mensaje indicando que el cajero ha comenzado a atender al cliente
        System.out.println("Cajero " + id + " comenzó a atender al cliente " + cliente.getId());
        
        // bucle que recorre las transacciones del cliente
        for (int transaccion : cliente.getTransacciones()) {
            
            try {
            	
                //simulación de la pausa
                Thread.sleep(transaccion);
                
            } catch (InterruptedException e) {
            	
                Thread.currentThread().interrupt();//manejo de errores
                
            }
            
            //mensaje de transaccion completada
            System.out.println("Cajero " + id + " completó la transacción del cliente " + cliente.getId() + " en " + transaccion + " ms");
        }
        
        //mensaje infromando que se ha terminado de tratar con un cliente
        System.out.println("Cajero " + id + " terminó de atender al cliente " + cliente.getId());
    }

    @Override
    public void run() {
        
        //bucle para atender clientes continuamente
        while (true) {
            
            //obtener el siguiente cliente desde la clase Main
            Cliente cliente = Main.siguienteCliente();
            
            if (cliente != null) {
            	
                //si hay un cliente disponible atenderlo
                atender(cliente);
                
            }
        }
    }
}

