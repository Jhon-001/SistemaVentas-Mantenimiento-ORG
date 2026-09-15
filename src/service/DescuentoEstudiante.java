package service;

public class DescuentoEstudiante implements IDescuentoStrategy {

    @Override
    public double aplicarDescuento(double monto) {
        return monto * 0.90;
    }
}