package service;

public class DescuentoEstudiante implements IDescuentoStrategy {
     @Override
    public double aplicar(double monto) {
        return monto * 0.10;
    }

}
