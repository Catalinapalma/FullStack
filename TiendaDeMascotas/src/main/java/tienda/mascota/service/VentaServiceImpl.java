package tienda.mascota.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import tienda.mascota.model.Producto;
import tienda.mascota.model.Venta;
import tienda.mascota.repository.ProductoRepository;
import tienda.mascota.repository.VentaRepository;

@Service
public class VentaServiceImpl implements VentaService {

    private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;

    public VentaServiceImpl(
            ProductoRepository productoRepository,
            VentaRepository ventaRepository) {
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarProducto(Long id, Producto producto) {
        if (productoRepository.existsById(id)) {
            producto.setId(id);
            return productoRepository.save(producto);
        }
        return null;
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Venta> obtenerVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public Venta crearVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public Venta actualizarVenta(Long id, Venta venta) {
        if (ventaRepository.existsById(id)) {
            venta.setId(id);
            return ventaRepository.save(venta);
        }
        return null;
    }

    @Override
    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }

    @Override
    public Map<String, Double> obtenerGanancias() {

        List<Venta> ventas = ventaRepository.findAll();

        double gananciaDiaria = ventas.stream()
                .filter(v -> v.getFecha().equals(LocalDate.of(2026, 8, 30)))
                .mapToDouble(this::calcularGanancia)
                .sum();

        double gananciaMensual = ventas.stream()
                .filter(v -> v.getFecha().getYear() == 2026
                        && v.getFecha().getMonthValue() == 8)
                .mapToDouble(this::calcularGanancia)
                .sum();

        double gananciaAnual = ventas.stream()
                .filter(v -> v.getFecha().getYear() == 2026)
                .mapToDouble(this::calcularGanancia)
                .sum();

        return Map.of(
                "gananciaDiaria", gananciaDiaria,
                "gananciaMensual", gananciaMensual,
                "gananciaAnual", gananciaAnual
        );
    }

    private double calcularGanancia(Venta venta) {
        Producto producto = venta.getProducto();

        return (producto.getPrecio() - producto.getCosto())
                * venta.getCantidad();
    }
}