package hito;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cuenta implements Serializable {
    private Cliente cliente;
    private double saldo;
    private List<Movimiento> movimientos;

    public Cuenta(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = 0.0;
        this.movimientos = new ArrayList<>();
    }

    public void ingresarFondos(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            Movimiento movimiento = new Movimiento("Ingreso", cantidad);
            movimientos.add(movimiento);
        }
    }

    public void retirarFondos(double cantidad) {
        if (cantidad > 0 && saldo >= cantidad) {
            saldo -= cantidad;
            Movimiento movimiento = new Movimiento("Retiro", cantidad);
            movimientos.add(movimiento);
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }
}