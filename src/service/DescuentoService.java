package service;

public class DescuentoService {

    public double calcularDescuento(double monto, String tipoCliente) {
        IDescuentoStrategy estrategia = DescuentoFactory.obtenerEstrategia(tipoCliente);
        return estrategia.aplicar(monto);
    }
}
