package service;

public class DescuentoFactory {
    public static IDescuentoStrategy obtenerEstrategia(String tipoCliente) {
        if (tipoCliente == null) return new DescuentoSinDescuento();

        switch (tipoCliente.toUpperCase()) {
            case "VIP":
                return new DescuentoVIP();
            case "NORMAL":
                return new DescuentoNormal();
            case "EMPRESA":
                return new DescuentoEmpresa();
            case "ESTUDIANTE":
                return new DescuentoEstudiante();
            default:
                return new DescuentoSinDescuento();
        }
    }

}
