package app;

import repository.ClienteRepository;
import repository.ProductoRepository;
import repository.VentaRepository;
import service.ClienteService;
import service.DescuentoService;
import service.ProductoService;
import service.ReporteService;
import service.VentaService;

public class Main {

    public static void main(String[] args) {

        ClienteRepository clienteRepository = new ClienteRepository();
        ProductoRepository productoRepository = new ProductoRepository();
        VentaRepository ventaRepository = new VentaRepository();

        ClienteService clienteService =
                new ClienteService(clienteRepository);

        ProductoService productoService =
                new ProductoService(productoRepository);

        VentaService ventaService =
                new VentaService(
                        clienteService,
                        productoService,
                        ventaRepository
                );

        ReporteService reporteService =
                new ReporteService(ventaRepository);

        System.out.println(
                "=== SISTEMA DE VENTAS - BASELINE v0.1.0 (16 SEMANAS) ==="
        );

        // =========================================================
        // DATOS INICIALES
        // =========================================================

        clienteService.registrarCliente(
                "12345678",
                "Juan Perez",
                "VIP"
        );

        clienteService.registrarCliente(
                "87654321",
                "Maria Lopez",
                "NORMAL"
        );

        clienteService.registrarCliente(
                "ABCD5678",
                "Cliente Bug",
                "NORMAL"
        );

        productoService.registrarProducto(
                1,
                "Laptop",
                2500
        );

        productoService.registrarProducto(
                2,
                "Mouse",
                50
        );

        productoService.registrarProducto(
                3,
                "Teclado",
                120
        );

        productoService.registrarProducto(
                4,
                "Monitor",
                900
        );

        productoService.registrarProducto(
                5,
                "Producto Bug",
                -100
        );

        // =========================================================
        // VENTA 1 - CLIENTE VIP
        // =========================================================

        ventaService.crearVenta("12345678");

        ventaService.agregarProductoVenta(1, 1);
        ventaService.agregarProductoVenta(2, 2);

        ventaService.finalizarVenta();

        // =========================================================
        // VENTA 2 - CLIENTE NORMAL
        // =========================================================

        ventaService.crearVenta("87654321");

        ventaService.agregarProductoVenta(3, 0);
        ventaService.agregarProductoVenta(4, 1);

        ventaService.finalizarVenta();

        // =========================================================
        // REPORTE
        // =========================================================

        reporteService.mostrarReporteVentas();
        reporteService.mostrarResumen();

        // =========================================================
        // PRUEBA DE ESTRATEGIAS DE DESCUENTO
        // =========================================================

        System.out.println();
        System.out.println(
                "=== PRUEBA DE ESTRATEGIAS DE DESCUENTO ==="
        );

        DescuentoService descuentoService =
                new DescuentoService();

        double montoPrueba = 100.00;

        // VIP - 20% de descuento
        double resultadoVIP =
                descuentoService.calcularDescuento(
                        "VIP",
                        montoPrueba
                );

        System.out.println(
                "VIP: S/ " + resultadoVIP
        );

        // NORMAL - sin descuento
        double resultadoNormal =
                descuentoService.calcularDescuento(
                        "NORMAL",
                        montoPrueba
                );

        System.out.println(
                "NORMAL: S/ " + resultadoNormal
        );

        // EMPRESA - 15% de descuento
        double resultadoEmpresa =
                descuentoService.calcularDescuento(
                        "EMPRESA",
                        montoPrueba
                );

        System.out.println(
                "EMPRESA: S/ " + resultadoEmpresa
        );

        // ESTUDIANTE - 10% de descuento
        double resultadoEstudiante =
                descuentoService.calcularDescuento(
                        "ESTUDIANTE",
                        montoPrueba
                );

        System.out.println(
                "ESTUDIANTE: S/ " + resultadoEstudiante
        );

        // =========================================================
        // PRUEBA DE NUEVA ESTRATEGIA: JUBILADO
        // =========================================================

        double resultadoJubilado =
                descuentoService.calcularDescuento(
                        "JUBILADO",
                        montoPrueba
                );

        System.out.println(
                "JUBILADO: S/ " + resultadoJubilado
        );

        System.out.println();
        System.out.println("=== FIN ===");
    }
}