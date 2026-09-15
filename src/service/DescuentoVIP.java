package service;

public class DescuentoVIP implements IDescuentoStrategy {

    @Override
    public double aplicarDescuento(double monto) {
        return monto * 0.80;
    }
}