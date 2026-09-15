package service;

public class DescuentoEmpresa implements IDescuentoStrategy {
     @Override
    public double aplicar(double monto) {
        return monto * 0.15;
    }

}
