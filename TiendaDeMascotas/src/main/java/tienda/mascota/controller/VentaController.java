package tienda.mascota.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tienda.mascota.model.Producto;
import tienda.mascota.model.Venta;
import tienda.mascota.service.VentaService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;


    // PRODUCTOS

    @GetMapping("/productos")
    public List<Producto> obtenerProductos() {
        return ventaService.obtenerProductos();
    }

    @GetMapping("/productos/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return ventaService.obtenerProductoPorId(id);
    }

    @PostMapping("/productos")
    public Producto crearProducto(@RequestBody Producto producto) {
        return ventaService.crearProducto(producto);
    }

    @PutMapping("/productos/{id}")
    public Producto actualizarProducto(
            @PathVariable Long id,
            @RequestBody Producto producto) {

        return ventaService.actualizarProducto(id, producto);
    }

    @DeleteMapping("/productos/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        ventaService.eliminarProducto(id);
    }


    // VENTAS

    @GetMapping("/ventas")
    public List<Venta> obtenerVentas() {
        return ventaService.obtenerVentas();
    }

    @GetMapping("/ventas/{id}")
    public Venta obtenerVentaPorId(@PathVariable Long id) {
        return ventaService.obtenerVentaPorId(id);
    }

    @PostMapping("/ventas")
    public Venta crearVenta(@RequestBody Venta venta) {
        return ventaService.crearVenta(venta);
    }

    @PutMapping("/ventas/{id}")
    public Venta actualizarVenta(
            @PathVariable Long id,
            @RequestBody Venta venta) {

        return ventaService.actualizarVenta(id, venta);
    }

    @DeleteMapping("/ventas/{id}")
    public void eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
    }


    // GANANCIAS

    @GetMapping("/ganancias")
    public Map<String, Double> obtenerGanancias() {
        return ventaService.obtenerGanancias();
    }
}