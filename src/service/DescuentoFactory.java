package service;

import java.util.HashMap;
import java.util.Map;

public class DescuentoFactory {

    private static final Map<String, IDescuentoStrategy> ESTRATEGIAS =
            new HashMap<>();

    static {

        ESTRATEGIAS.put(
                "VIP",
                new DescuentoVIP()
        );

        ESTRATEGIAS.put(
                "NORMAL",
                new DescuentoNormal()
        );

        ESTRATEGIAS.put(
                "EMPRESA",
                new DescuentoEmpresa()
        );

        ESTRATEGIAS.put(
                "ESTUDIANTE",
                new DescuentoEstudiante()
        );

        ESTRATEGIAS.put(
                "JUBILADO",
                new DescuentoJubilado()
        );
    }

    public static IDescuentoStrategy obtenerEstrategia(
            String tipoCliente) {

        if (tipoCliente == null) {
            return ESTRATEGIAS.get("NORMAL");
        }

        return ESTRATEGIAS.getOrDefault(
                tipoCliente.trim().toUpperCase(),
                new DescuentoNormal()
        );
    }
}