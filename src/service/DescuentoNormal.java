package service;

public class DescuentoNormal implements IDescuentoStrategy {

    @Override
    public double aplicarDescuento(double monto) {
        return monto;
    }
}