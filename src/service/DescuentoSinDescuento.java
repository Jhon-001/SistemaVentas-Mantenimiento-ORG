package service;

public class DescuentoSinDescuento implements IDescuentoStrategy {
    @Override
    public double aplicar(double monto) {
        return 0;
    }
}

