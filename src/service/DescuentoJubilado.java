package service;

public class DescuentoJubilado implements IDescuentoStrategy {

    @Override
    public double aplicarDescuento(double monto) {
        return monto * 0.75;
    }
}