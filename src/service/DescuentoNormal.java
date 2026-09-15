package service;

public class DescuentoNormal implements IDescuentoStrategy {
     @Override
    public double aplicar(double monto) {
        return monto * 0.05;
    }

}
