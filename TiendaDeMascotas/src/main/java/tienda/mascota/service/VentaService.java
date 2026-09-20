package tienda.mascota.service;

import java.util.List;
import java.util.Map;

import tienda.mascota.model.Producto;
import tienda.mascota.model.Venta;

public interface VentaService {

    // Productos
    List<Producto> obtenerProductos();
    Producto obtenerProductoPorId(Long id);
    Producto crearProducto(Producto producto);
    Producto actualizarProducto(Long id, Producto producto);
    void eliminarProducto(Long id);

    // Ventas
    List<Venta> obtenerVentas();
    Venta obtenerVentaPorId(Long id);
    Venta crearVenta(Venta venta);
    Venta actualizarVenta(Long id, Venta venta);
    void eliminarVenta(Long id);

    // Ganancias
    Map<String, Double> obtenerGanancias();
}