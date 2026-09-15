package service;

public class DescuentoService {

    public double calcularDescuento(String tipoCliente, double monto) {

        IDescuentoStrategy estrategia =
                DescuentoFactory.obtenerEstrategia(tipoCliente);

        return estrategia.aplicarDescuento(monto);
    }
}