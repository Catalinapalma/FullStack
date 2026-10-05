package tienda.mascota.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import tienda.mascota.model.Producto;
import tienda.mascota.model.Venta;
import tienda.mascota.service.VentaService;

@WebMvcTest(VentaController.class)
class VentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VentaService ventaService;

    // =========================
    // PRODUCTOS
    // =========================

    @Test
    void obtenerProductosDebeRetornarLista() throws Exception {

        Producto p1 =
                new Producto(1L, "Alimento", "Alimentos", 25000.0, 15000.0);

        Producto p2 =
                new Producto(2L, "Collar", "Accesorios", 10000.0, 6000.0);

        when(ventaService.obtenerProductos())
                .thenReturn(List.of(p1, p2));

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Alimento"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nombre").value("Collar"));
    }

    @Test
    void obtenerProductoPorIdDebeRetornarProducto() throws Exception {

        Producto producto =
                new Producto(1L, "Alimento", "Alimentos", 25000.0, 15000.0);

        when(ventaService.obtenerProductoPorId(1L))
                .thenReturn(producto);

        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Alimento"))
                .andExpect(jsonPath("$.categoria").value("Alimentos"))
                .andExpect(jsonPath("$.precio").value(25000.0))
                .andExpect(jsonPath("$.costo").value(15000.0));
    }

    @Test
    void crearProductoDebeRetornarProducto() throws Exception {

        Producto producto =
                new Producto(1L, "Collar", "Accesorios", 10000.0, 6000.0);

        when(ventaService.crearProducto(any(Producto.class)))
                .thenReturn(producto);

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "id": 1,
                                  "nombre": "Collar",
                                  "categoria": "Accesorios",
                                  "precio": 10000.0,
                                  "costo": 6000.0
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Collar"));
    }

    @Test
    void actualizarProductoDebeRetornarProductoActualizado() throws Exception {

        Producto producto =
                new Producto(1L, "Collar Premium", "Accesorios", 12000.0, 6000.0);

        when(ventaService.actualizarProducto(
                eq(1L),
                any(Producto.class)))
                .thenReturn(producto);

        mockMvc.perform(put("/api/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nombre": "Collar Premium",
                                  "categoria": "Accesorios",
                                  "precio": 12000.0,
                                  "costo": 6000.0
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Collar Premium"));
    }

    @Test
    void eliminarProductoDebeResponderOk() throws Exception {

        doNothing().when(ventaService).eliminarProducto(1L);

        mockMvc.perform(delete("/api/productos/1"))
                .andExpect(status().isOk());
    }

    // =========================
    // VENTAS
    // =========================

    @Test
    void obtenerVentasDebeRetornarLista() throws Exception {

        Producto producto =
                new Producto(1L, "Alimento", "Alimentos", 25000.0, 15000.0);

        Venta venta =
                new Venta(1L, producto, 2, LocalDate.of(2026, 8, 30));

        when(ventaService.obtenerVentas())
                .thenReturn(List.of(venta));

        mockMvc.perform(get("/api/ventas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].cantidad").value(2))
                .andExpect(jsonPath("$[0].producto.id").value(1))
                .andExpect(jsonPath("$[0].fecha").value("2026-08-30"));
    }

    @Test
    void obtenerVentaPorIdDebeRetornarVenta() throws Exception {

        Producto producto =
                new Producto(1L, "Alimento", "Alimentos", 25000.0, 15000.0);

        Venta venta =
                new Venta(1L, producto, 2, LocalDate.of(2026, 8, 30));

        when(ventaService.obtenerVentaPorId(1L))
                .thenReturn(venta);

        mockMvc.perform(get("/api/ventas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cantidad").value(2))
                .andExpect(jsonPath("$.producto.nombre").value("Alimento"))
                .andExpect(jsonPath("$.fecha").value("2026-08-30"));
    }

    @Test
    void crearVentaDebeRetornarVenta() throws Exception {

        Producto producto =
                new Producto(1L, "Alimento", "Alimentos", 25000.0, 15000.0);

        Venta venta =
                new Venta(1L, producto, 2, LocalDate.of(2026, 8, 30));

        when(ventaService.crearVenta(any(Venta.class)))
                .thenReturn(venta);

        mockMvc.perform(post("/api/ventas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "id": 1,
                                  "producto": {
                                    "id": 1,
                                    "nombre": "Alimento",
                                    "categoria": "Alimentos",
                                    "precio": 25000.0,
                                    "costo": 15000.0
                                  },
                                  "cantidad": 2,
                                  "fecha": "2026-08-30"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cantidad").value(2));
    }

    @Test
    void actualizarVentaDebeRetornarVentaActualizada() throws Exception {

        Producto producto =
                new Producto(1L, "Alimento", "Alimentos", 25000.0, 15000.0);

        Venta venta =
                new Venta(1L, producto, 3, LocalDate.of(2026, 8, 30));

        when(ventaService.actualizarVenta(
                eq(1L),
                any(Venta.class)))
                .thenReturn(venta);

        mockMvc.perform(put("/api/ventas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "producto": {
                                    "id": 1,
                                    "nombre": "Alimento",
                                    "categoria": "Alimentos",
                                    "precio": 25000.0,
                                    "costo": 15000.0
                                  },
                                  "cantidad": 3,
                                  "fecha": "2026-08-30"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cantidad").value(3));
    }

    @Test
    void eliminarVentaDebeResponderOk() throws Exception {

        doNothing().when(ventaService).eliminarVenta(1L);

        mockMvc.perform(delete("/api/ventas/1"))
                .andExpect(status().isOk());
    }

    // =========================
    // GANANCIAS
    // =========================

    @Test
    void obtenerGananciasDebeRetornarGanancias() throws Exception {

        Map<String, Double> ganancias = Map.of(
                "gananciaDiaria", 20000.0,
                "gananciaMensual", 30000.0,
                "gananciaAnual", 60000.0
        );

        when(ventaService.obtenerGanancias())
                .thenReturn(ganancias);

        mockMvc.perform(get("/api/ganancias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gananciaDiaria").value(20000.0))
                .andExpect(jsonPath("$.gananciaMensual").value(30000.0))
                .andExpect(jsonPath("$.gananciaAnual").value(60000.0));
    }
}