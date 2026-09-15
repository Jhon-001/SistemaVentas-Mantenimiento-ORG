package service;

public class DescuentoEmpresa implements IDescuentoStrategy {

    @Override
    public double aplicarDescuento(double monto) {
        return monto * 0.85;
    }
}