package service;

import model.Cliente;
import model.Producto;
import model.Venta;
import repository.VentaRepository;
import util.Console;
import util.Validaciones;

public class VentaService {

    private ClienteService clienteService;
    private ProductoService productoService;
    private VentaRepository ventaRepo;
    private DescuentoService descuentoService;

    private Venta ventaActual;

    public VentaService(
            ClienteService clienteService,
            ProductoService productoService,
            VentaRepository ventaRepo) {

        this.clienteService = clienteService;
        this.productoService = productoService;
        this.ventaRepo = ventaRepo;
        this.descuentoService = new DescuentoService();
    }

    public void crearVenta(String dniCliente) {

        Cliente cliente = clienteService.buscarCliente(dniCliente);

        if (cliente == null) {
            Console.error("Cliente no existe");
            return;
        }

        ventaActual = new Venta(cliente);

        Console.info(
                "Venta creada para: "
                + cliente.getNombre()
                + " | Tipo: "
                + cliente.getTipo()
        );
    }

    // BUG intencional: permite cantidad 0 o negativa por Validaciones
    // Code smell: repetición de mensajes y validaciones
    public void agregarProductoVenta(int idProducto, int cantidad) {

        if (ventaActual == null) {
            Console.error("No hay venta activa");
            return;
        }

        Producto producto = productoService.buscarProducto(idProducto);

        if (producto == null) {
            Console.error("Producto no encontrado");
            return;
        }

        if (!Validaciones.validarCantidad(cantidad)) {
            Console.error("Cantidad inválida");
            return;
        }

        ventaActual.agregarDetalle(producto, cantidad);

        Console.info(
                "Producto agregado: "
                + producto.getNombre()
                + " x"
                + cantidad
        );
    }

    public void finalizarVenta() {

        if (ventaActual == null) {
            Console.error("No hay venta activa");
            return;
        }

        ventaActual.finalizar();
        ventaRepo.guardar(ventaActual);

        double subtotal = ventaActual.calcularTotal();

        double totalConDescuento =
                descuentoService.calcularDescuento(
                        ventaActual.getCliente().getTipo(),
                        subtotal
                );

        Console.info("Cliente: "
                + ventaActual.getCliente().getNombre());

        Console.info("Tipo de cliente: "
                + ventaActual.getCliente().getTipo());

        Console.info("Subtotal: S/ "
                + String.format("%.2f", subtotal));

        Console.info("Total con descuento: S/ "
                + String.format("%.2f", totalConDescuento));

        ventaActual = null;
    }

    public Venta obtenerVentaActual() {
        return ventaActual;
    }
}