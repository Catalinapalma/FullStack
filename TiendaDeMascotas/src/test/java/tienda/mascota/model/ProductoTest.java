package tienda.mascota.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class ProductoTest {

    @Test
    void debeCrearProductoCorrectamente() {

        Producto producto = new Producto(
                1L,
                "Alimento Premium",
                "Alimentos",
                25000.0,
                15000.0
        );

        assertNotNull(producto);
        assertEquals(1L, producto.getId());
        assertEquals("Alimento Premium", producto.getNombre());
        assertEquals("Alimentos", producto.getCategoria());
        assertEquals(25000.0, producto.getPrecio());
        assertEquals(15000.0, producto.getCosto());
    }

    @Test
    void debeModificarProductoCorrectamente() {

        Producto producto = new Producto();

        producto.setId(2L);
        producto.setNombre("Collar");
        producto.setCategoria("Accesorios");
        producto.setPrecio(10000.0);
        producto.setCosto(6000.0);

        assertEquals(2L, producto.getId());
        assertEquals("Collar", producto.getNombre());
        assertEquals("Accesorios", producto.getCategoria());
        assertEquals(10000.0, producto.getPrecio());
        assertEquals(6000.0, producto.getCosto());
    }
}