package publicaciones.publicaciones.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
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

import publicaciones.publicaciones.model.Calificacion;
import publicaciones.publicaciones.model.Comentario;
import publicaciones.publicaciones.model.Publicacion;
import publicaciones.publicaciones.service.PublicacionService;

@WebMvcTest(PublicacionController.class)
class PublicacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PublicacionService publicacionService;

    // =========================
    // PUBLICACIONES
    // =========================

    @Test
    void obtenerPublicacionesDebeRetornarLista() throws Exception {

        Publicacion p1 =
                new Publicacion(1L, "Spring Boot", "Contenido 1", "Catalina");

        Publicacion p2 =
                new Publicacion(2L, "Docker", "Contenido 2", "Catalina");

        when(publicacionService.obtenerPublicaciones())
                .thenReturn(List.of(p1, p2));

        mockMvc.perform(get("/api/publicaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Spring Boot"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].titulo").value("Docker"));
    }

    @Test
    void obtenerPublicacionPorIdDebeRetornarPublicacion() throws Exception {

        Publicacion publicacion =
                new Publicacion(1L, "JUnit", "Pruebas unitarias", "Catalina");

        when(publicacionService.obtenerPublicacionPorId(1L))
                .thenReturn(publicacion);

        mockMvc.perform(get("/api/publicaciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("JUnit"))
                .andExpect(jsonPath("$.contenido").value("Pruebas unitarias"))
                .andExpect(jsonPath("$.autor").value("Catalina"));
    }

    @Test
    void crearPublicacionDebeRetornarPublicacion() throws Exception {

        Publicacion publicacion =
                new Publicacion(1L, "Docker", "Contenedores", "Catalina");

        when(publicacionService.crearPublicacion(
                org.mockito.ArgumentMatchers.any(Publicacion.class)))
                .thenReturn(publicacion);

        mockMvc.perform(post("/api/publicaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "id": 1,
                                  "titulo": "Docker",
                                  "contenido": "Contenedores",
                                  "autor": "Catalina"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Docker"));
    }

    @Test
    void actualizarPublicacionDebeRetornarPublicacionActualizada() throws Exception {

        Publicacion publicacion =
                new Publicacion(1L, "Actualizada", "Nuevo contenido", "Catalina");

        when(publicacionService.actualizarPublicacion(
                org.mockito.ArgumentMatchers.eq(1L),
                org.mockito.ArgumentMatchers.any(Publicacion.class)))
                .thenReturn(publicacion);

        mockMvc.perform(put("/api/publicaciones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "titulo": "Actualizada",
                                  "contenido": "Nuevo contenido",
                                  "autor": "Catalina"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Actualizada"));
    }

    @Test
    void eliminarPublicacionDebeResponderOk() throws Exception {

        doNothing().when(publicacionService).eliminarPublicacion(1L);

        mockMvc.perform(delete("/api/publicaciones/1"))
                .andExpect(status().isOk());
    }

    // =========================
    // COMENTARIOS
    // =========================

    @Test
    void obtenerComentariosDebeRetornarLista() throws Exception {

        Comentario comentario =
                new Comentario(1L, 1L, "Catalina", "Excelente publicación");

        when(publicacionService.obtenerComentarios())
                .thenReturn(List.of(comentario));

        mockMvc.perform(get("/api/comentarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].publicacionId").value(1))
                .andExpect(jsonPath("$[0].comentario")
                        .value("Excelente publicación"));
    }

    @Test
    void obtenerComentarioPorIdDebeRetornarComentario() throws Exception {

        Comentario comentario =
                new Comentario(1L, 1L, "Catalina", "Comentario de prueba");

        when(publicacionService.obtenerComentarioPorId(1L))
                .thenReturn(comentario);

        mockMvc.perform(get("/api/comentarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.autor").value("Catalina"))
                .andExpect(jsonPath("$.comentario")
                        .value("Comentario de prueba"));
    }

    @Test
    void crearComentarioDebeRetornarComentario() throws Exception {

        Comentario comentario =
                new Comentario(1L, 1L, "Catalina", "Nuevo comentario");

        when(publicacionService.crearComentario(
                org.mockito.ArgumentMatchers.any(Comentario.class)))
                .thenReturn(comentario);

        mockMvc.perform(post("/api/comentarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "id": 1,
                                  "publicacionId": 1,
                                  "autor": "Catalina",
                                  "comentario": "Nuevo comentario"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.comentario")
                        .value("Nuevo comentario"));
    }

    @Test
    void actualizarComentarioDebeRetornarComentarioActualizado() throws Exception {

        Comentario comentario =
                new Comentario(1L, 1L, "Catalina", "Comentario actualizado");

        when(publicacionService.actualizarComentario(
                org.mockito.ArgumentMatchers.eq(1L),
                org.mockito.ArgumentMatchers.any(Comentario.class)))
                .thenReturn(comentario);

        mockMvc.perform(put("/api/comentarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "publicacionId": 1,
                                  "autor": "Catalina",
                                  "comentario": "Comentario actualizado"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.comentario")
                        .value("Comentario actualizado"));
    }

    @Test
    void eliminarComentarioDebeResponderOk() throws Exception {

        doNothing().when(publicacionService).eliminarComentario(1L);

        mockMvc.perform(delete("/api/comentarios/1"))
                .andExpect(status().isOk());
    }

    // =========================
    // CALIFICACIONES
    // =========================

    @Test
    void obtenerCalificacionesDebeRetornarLista() throws Exception {

        Calificacion calificacion =
                new Calificacion(1L, 1L, 5);

        when(publicacionService.obtenerCalificaciones())
                .thenReturn(List.of(calificacion));

        mockMvc.perform(get("/api/calificaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].publicacionId").value(1))
                .andExpect(jsonPath("$[0].puntuacion").value(5));
    }

    @Test
    void obtenerCalificacionPorIdDebeRetornarCalificacion() throws Exception {

        Calificacion calificacion =
                new Calificacion(1L, 1L, 5);

        when(publicacionService.obtenerCalificacionPorId(1L))
                .thenReturn(calificacion);

        mockMvc.perform(get("/api/calificaciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.puntuacion").value(5));
    }

    @Test
    void crearCalificacionDebeRetornarCalificacion() throws Exception {

        Calificacion calificacion =
                new Calificacion(1L, 1L, 5);

        when(publicacionService.crearCalificacion(
                org.mockito.ArgumentMatchers.any(Calificacion.class)))
                .thenReturn(calificacion);

        mockMvc.perform(post("/api/calificaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "id": 1,
                                  "publicacionId": 1,
                                  "puntuacion": 5
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.puntuacion").value(5));
    }

    @Test
    void actualizarCalificacionDebeRetornarCalificacionActualizada() throws Exception {

        Calificacion calificacion =
                new Calificacion(1L, 1L, 4);

        when(publicacionService.actualizarCalificacion(
                org.mockito.ArgumentMatchers.eq(1L),
                org.mockito.ArgumentMatchers.any(Calificacion.class)))
                .thenReturn(calificacion);

        mockMvc.perform(put("/api/calificaciones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "publicacionId": 1,
                                  "puntuacion": 4
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.puntuacion").value(4));
    }

    @Test
    void eliminarCalificacionDebeResponderOk() throws Exception {

        doNothing().when(publicacionService).eliminarCalificacion(1L);

        mockMvc.perform(delete("/api/calificaciones/1"))
                .andExpect(status().isOk());
    }

    // =========================
    // PROMEDIOS
    // =========================

    @Test
    void obtenerPromediosDebeRetornarPromedios() throws Exception {

        Map<String, Double> promedios = new LinkedHashMap<>();
        promedios.put("publicacion1", 4.5);
        promedios.put("publicacion2", 3.0);

        when(publicacionService.obtenerPromedios())
                .thenReturn(promedios);

        mockMvc.perform(get("/api/promedios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.publicacion1").value(4.5))
                .andExpect(jsonPath("$.publicacion2").value(3.0));
    }
}