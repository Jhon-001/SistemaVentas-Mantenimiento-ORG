package service;

public class DescuentoSinDescuento implements IDescuentoStrategy {

    @Override
    public double aplicarDescuento(double monto) {
        return monto;
    }
}