package tienda.mascota.model;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class VentaTest {

    @Test
    void debeCrearVentaCorrectamente() {

        Producto producto = new Producto(
                1L,
                "Alimento Premium",
                "Alimentos",
                25000.0,
                15000.0
        );

        LocalDate fecha = LocalDate.of(2026, 8, 30);

        Venta venta = new Venta(
                1L,
                producto,
                2,
                fecha
        );

        assertNotNull(venta);
        assertEquals(1L, venta.getId());
        assertEquals(producto, venta.getProducto());
        assertEquals(2, venta.getCantidad());
        assertEquals(fecha, venta.getFecha());
    }

    @Test
    void debeModificarVentaCorrectamente() {

        Producto producto = new Producto(
                2L,
                "Collar",
                "Accesorios",
                10000.0,
                6000.0
        );

        LocalDate fecha = LocalDate.of(2026, 9, 1);

        Venta venta = new Venta();

        venta.setId(2L);
        venta.setProducto(producto);
        venta.setCantidad(3);
        venta.setFecha(fecha);

        assertEquals(2L, venta.getId());
        assertEquals(producto, venta.getProducto());
        assertEquals(3, venta.getCantidad());
        assertEquals(fecha, venta.getFecha());
    }
}