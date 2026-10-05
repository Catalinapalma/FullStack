package tienda.mascota.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import tienda.mascota.model.Producto;
import tienda.mascota.model.Venta;
import tienda.mascota.repository.ProductoRepository;
import tienda.mascota.repository.VentaRepository;

@ExtendWith(MockitoExtension.class)
class VentaServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private VentaRepository ventaRepository;

    private VentaServiceImpl ventaService;

    @BeforeEach
    void setUp() {
        ventaService = new VentaServiceImpl(
                productoRepository,
                ventaRepository
        );
    }

    // =========================
    // PRODUCTOS
    // =========================

    @Test
    void obtenerProductosDebeRetornarLista() {

        Producto p1 = new Producto(
                1L, "Alimento", "Alimentos", 25000.0, 15000.0);

        Producto p2 = new Producto(
                2L, "Collar", "Accesorios", 10000.0, 6000.0);

        when(productoRepository.findAll())
                .thenReturn(List.of(p1, p2));

        List<Producto> resultado = ventaService.obtenerProductos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        verify(productoRepository).findAll();
    }

    @Test
    void obtenerProductoPorIdDebeRetornarProducto() {

        Producto producto = new Producto(
                1L, "Alimento", "Alimentos", 25000.0, 15000.0);

        when(productoRepository.findById(1L))
                .thenReturn(Optional.of(producto));

        Producto resultado = ventaService.obtenerProductoPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Alimento", resultado.getNombre());
    }

    @Test
    void obtenerProductoPorIdInexistenteDebeRetornarNull() {

        when(productoRepository.findById(99L))
                .thenReturn(Optional.empty());

        Producto resultado = ventaService.obtenerProductoPorId(99L);

        assertNull(resultado);
    }

    @Test
    void crearProductoDebeGuardarProducto() {

        Producto producto = new Producto(
                1L, "Alimento", "Alimentos", 25000.0, 15000.0);

        when(productoRepository.save(producto))
                .thenReturn(producto);

        Producto resultado = ventaService.crearProducto(producto);

        assertNotNull(resultado);
        assertEquals("Alimento", resultado.getNombre());

        verify(productoRepository).save(producto);
    }

    @Test
    void actualizarProductoExistenteDebeGuardarCambios() {

        Producto producto = new Producto(
                null, "Collar", "Accesorios", 12000.0, 6000.0);

        when(productoRepository.existsById(1L))
                .thenReturn(true);

        when(productoRepository.save(producto))
                .thenReturn(producto);

        Producto resultado =
                ventaService.actualizarProducto(1L, producto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Collar", resultado.getNombre());

        verify(productoRepository).save(producto);
    }

    @Test
    void actualizarProductoInexistenteDebeRetornarNull() {

        Producto producto = new Producto(
                null, "Collar", "Accesorios", 12000.0, 6000.0);

        when(productoRepository.existsById(99L))
                .thenReturn(false);

        Producto resultado =
                ventaService.actualizarProducto(99L, producto);

        assertNull(resultado);

        verify(productoRepository, never())
                .save(org.mockito.ArgumentMatchers.any(Producto.class));
    }

    @Test
    void eliminarProductoDebeEliminarPorId() {

        ventaService.eliminarProducto(1L);

        verify(productoRepository).deleteById(1L);
    }

    // =========================
    // VENTAS
    // =========================

    @Test
    void obtenerVentasDebeRetornarLista() {

        Producto producto = new Producto(
                1L, "Alimento", "Alimentos", 25000.0, 15000.0);

        Venta venta = new Venta(
                1L,
                producto,
                2,
                LocalDate.of(2026, 8, 30)
        );

        when(ventaRepository.findAll())
                .thenReturn(List.of(venta));

        List<Venta> resultado = ventaService.obtenerVentas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());

        verify(ventaRepository).findAll();
    }

    @Test
    void obtenerVentaPorIdDebeRetornarVenta() {

        Producto producto = new Producto(
                1L, "Alimento", "Alimentos", 25000.0, 15000.0);

        Venta venta = new Venta(
                1L,
                producto,
                2,
                LocalDate.of(2026, 8, 30)
        );

        when(ventaRepository.findById(1L))
                .thenReturn(Optional.of(venta));

        Venta resultado = ventaService.obtenerVentaPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(2, resultado.getCantidad());
    }

    @Test
    void obtenerVentaPorIdInexistenteDebeRetornarNull() {

        when(ventaRepository.findById(99L))
                .thenReturn(Optional.empty());

        Venta resultado = ventaService.obtenerVentaPorId(99L);

        assertNull(resultado);
    }

    @Test
    void crearVentaDebeGuardarVenta() {

        Producto producto = new Producto(
                1L, "Alimento", "Alimentos", 25000.0, 15000.0);

        Venta venta = new Venta(
                1L,
                producto,
                2,
                LocalDate.of(2026, 8, 30)
        );

        when(ventaRepository.save(venta))
                .thenReturn(venta);

        Venta resultado = ventaService.crearVenta(venta);

        assertNotNull(resultado);
        assertEquals(2, resultado.getCantidad());

        verify(ventaRepository).save(venta);
    }

    @Test
    void actualizarVentaExistenteDebeGuardarCambios() {

        Producto producto = new Producto(
                1L, "Alimento", "Alimentos", 25000.0, 15000.0);

        Venta venta = new Venta(
                null,
                producto,
                3,
                LocalDate.of(2026, 8, 30)
        );

        when(ventaRepository.existsById(1L))
                .thenReturn(true);

        when(ventaRepository.save(venta))
                .thenReturn(venta);

        Venta resultado =
                ventaService.actualizarVenta(1L, venta);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(3, resultado.getCantidad());

        verify(ventaRepository).save(venta);
    }

    @Test
    void actualizarVentaInexistenteDebeRetornarNull() {

        Producto producto = new Producto(
                1L, "Alimento", "Alimentos", 25000.0, 15000.0);

        Venta venta = new Venta(
                null,
                producto,
                3,
                LocalDate.of(2026, 8, 30)
        );

        when(ventaRepository.existsById(99L))
                .thenReturn(false);

        Venta resultado =
                ventaService.actualizarVenta(99L, venta);

        assertNull(resultado);

        verify(ventaRepository, never())
                .save(org.mockito.ArgumentMatchers.any(Venta.class));
    }

    @Test
    void eliminarVentaDebeEliminarPorId() {

        ventaService.eliminarVenta(1L);

        verify(ventaRepository).deleteById(1L);
    }

    // =========================
    // GANANCIAS
    // =========================

    @Test
    void obtenerGananciasDebeCalcularDiariaMensualYAnual() {

        Producto producto = new Producto(
                1L,
                "Alimento",
                "Alimentos",
                25000.0,
                15000.0
        );

        // Ganancia: (25.000 - 15.000) * 2 = 20.000
        Venta ventaDiaria = new Venta(
                1L,
                producto,
                2,
                LocalDate.of(2026, 8, 30)
        );

        // Ganancia: 10.000
        Venta ventaMismoMes = new Venta(
                2L,
                producto,
                1,
                LocalDate.of(2026, 8, 15)
        );

        // Ganancia: 30.000
        Venta ventaMismoAnio = new Venta(
                3L,
                producto,
                3,
                LocalDate.of(2026, 9, 10)
        );

        // Año distinto: no debe entrar en ganancia anual 2026
        Venta ventaOtroAnio = new Venta(
                4L,
                producto,
                5,
                LocalDate.of(2025, 8, 30)
        );

        when(ventaRepository.findAll())
                .thenReturn(List.of(
                        ventaDiaria,
                        ventaMismoMes,
                        ventaMismoAnio,
                        ventaOtroAnio
                ));

        Map<String, Double> resultado =
                ventaService.obtenerGanancias();

        assertEquals(20000.0, resultado.get("gananciaDiaria"));
        assertEquals(30000.0, resultado.get("gananciaMensual"));
        assertEquals(60000.0, resultado.get("gananciaAnual"));
    }

    @Test
    void obtenerGananciasSinVentasDebeRetornarCero() {

        when(ventaRepository.findAll())
                .thenReturn(List.of());

        Map<String, Double> resultado =
                ventaService.obtenerGanancias();

        assertEquals(0.0, resultado.get("gananciaDiaria"));
        assertEquals(0.0, resultado.get("gananciaMensual"));
        assertEquals(0.0, resultado.get("gananciaAnual"));
    }
}