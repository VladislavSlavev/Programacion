package ejercicio;

class Cajero implements Runnable {
    private int id;

    public Cajero(int id) {
        this.id = id;
    }

    public void atender(Cliente cliente) {
        System.out.println("Cajero " + id + " comenzó a atender al cliente " + cliente.getId());
        for (int transaccion : cliente.getTransacciones()) {
            try {
                Thread.sleep(transaccion);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Cajero " + id + " completó la transacción del cliente " + cliente.getId() + " en " + transaccion + " ms");
        }
        System.out.println("Cajero " + id + " terminó de atender al cliente " + cliente.getId());
    }

    @Override
    public void run() {
        while (true) {
            Cliente cliente = Main.siguienteCliente();
            if (cliente != null) {
                atender(cliente);
            }
        }
    }
}
