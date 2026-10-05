package publicaciones.publicaciones.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import publicaciones.publicaciones.model.Calificacion;
import publicaciones.publicaciones.model.Comentario;
import publicaciones.publicaciones.model.Publicacion;
import publicaciones.publicaciones.repository.CalificacionRepository;
import publicaciones.publicaciones.repository.ComentarioRepository;
import publicaciones.publicaciones.repository.PublicacionRepository;

@ExtendWith(MockitoExtension.class)
class PublicacionServiceImplTest {

    @Mock
    private PublicacionRepository publicacionRepository;

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private CalificacionRepository calificacionRepository;

    private PublicacionServiceImpl publicacionService;

    @BeforeEach
    void setUp() {
        publicacionService = new PublicacionServiceImpl(
                publicacionRepository,
                comentarioRepository,
                calificacionRepository
        );
    }

    // =========================
    // PUBLICACIONES
    // =========================

    @Test
    void obtenerPublicacionesDebeRetornarLista() {
        Publicacion p1 =
                new Publicacion(1L, "Spring Boot", "Contenido 1", "Catalina");

        Publicacion p2 =
                new Publicacion(2L, "Docker", "Contenido 2", "Catalina");

        when(publicacionRepository.findAll())
                .thenReturn(List.of(p1, p2));

        List<Publicacion> resultado =
                publicacionService.obtenerPublicaciones();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        verify(publicacionRepository).findAll();
    }

    @Test
    void obtenerPublicacionPorIdDebeRetornarPublicacion() {
        Publicacion publicacion =
                new Publicacion(1L, "JUnit", "Pruebas", "Catalina");

        when(publicacionRepository.findById(1L))
                .thenReturn(Optional.of(publicacion));

        Publicacion resultado =
                publicacionService.obtenerPublicacionPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("JUnit", resultado.getTitulo());
    }

    @Test
    void obtenerPublicacionPorIdInexistenteDebeRetornarNull() {
        when(publicacionRepository.findById(99L))
                .thenReturn(Optional.empty());

        Publicacion resultado =
                publicacionService.obtenerPublicacionPorId(99L);

        assertNull(resultado);
    }

    @Test
    void crearPublicacionDebeGuardarPublicacion() {
        Publicacion publicacion =
                new Publicacion(1L, "JUnit", "Pruebas unitarias", "Catalina");

        when(publicacionRepository.save(publicacion))
                .thenReturn(publicacion);

        Publicacion resultado =
                publicacionService.crearPublicacion(publicacion);

        assertNotNull(resultado);
        assertEquals("JUnit", resultado.getTitulo());

        verify(publicacionRepository).save(publicacion);
    }

    @Test
    void actualizarPublicacionExistenteDebeGuardarCambios() {
        Publicacion publicacion =
                new Publicacion(null, "Actualizada", "Nuevo contenido", "Catalina");

        when(publicacionRepository.existsById(1L))
                .thenReturn(true);

        when(publicacionRepository.save(publicacion))
                .thenReturn(publicacion);

        Publicacion resultado =
                publicacionService.actualizarPublicacion(1L, publicacion);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Actualizada", resultado.getTitulo());

        verify(publicacionRepository).save(publicacion);
    }

    @Test
    void actualizarPublicacionInexistenteDebeRetornarNull() {
        Publicacion publicacion =
                new Publicacion(null, "Título", "Contenido", "Catalina");

        when(publicacionRepository.existsById(99L))
                .thenReturn(false);

        Publicacion resultado =
                publicacionService.actualizarPublicacion(99L, publicacion);

        assertNull(resultado);

        verify(publicacionRepository, never()).save(any());
    }

    @Test
    void eliminarPublicacionDebeEliminarPorId() {
        publicacionService.eliminarPublicacion(1L);

        verify(publicacionRepository).deleteById(1L);
    }

    // =========================
    // COMENTARIOS
    // =========================

    @Test
    void obtenerComentariosDebeRetornarLista() {
        Comentario comentario =
                new Comentario(1L, 1L, "Catalina", "Buen contenido");

        when(comentarioRepository.findAll())
                .thenReturn(List.of(comentario));

        List<Comentario> resultado =
                publicacionService.obtenerComentarios();

        assertEquals(1, resultado.size());
        assertEquals("Buen contenido", resultado.get(0).getComentario());
    }

    @Test
    void obtenerComentarioPorIdDebeRetornarComentario() {
        Comentario comentario =
                new Comentario(1L, 1L, "Catalina", "Comentario");

        when(comentarioRepository.findById(1L))
                .thenReturn(Optional.of(comentario));

        Comentario resultado =
                publicacionService.obtenerComentarioPorId(1L);

        assertNotNull(resultado);
        assertEquals("Comentario", resultado.getComentario());
    }

    @Test
    void obtenerComentarioPorIdInexistenteDebeRetornarNull() {
        when(comentarioRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertNull(publicacionService.obtenerComentarioPorId(99L));
    }

    @Test
    void crearComentarioDebeGuardarComentario() {
        Comentario comentario =
                new Comentario(1L, 1L, "Catalina", "Excelente");

        when(comentarioRepository.save(comentario))
                .thenReturn(comentario);

        Comentario resultado =
                publicacionService.crearComentario(comentario);

        assertNotNull(resultado);
        assertEquals("Excelente", resultado.getComentario());

        verify(comentarioRepository).save(comentario);
    }

    @Test
    void actualizarComentarioExistenteDebeGuardarCambios() {
        Comentario comentario =
                new Comentario(null, 1L, "Catalina", "Actualizado");

        when(comentarioRepository.existsById(1L))
                .thenReturn(true);

        when(comentarioRepository.save(comentario))
                .thenReturn(comentario);

        Comentario resultado =
                publicacionService.actualizarComentario(1L, comentario);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());

        verify(comentarioRepository).save(comentario);
    }

    @Test
    void actualizarComentarioInexistenteDebeRetornarNull() {
        Comentario comentario =
                new Comentario(null, 1L, "Catalina", "Comentario");

        when(comentarioRepository.existsById(99L))
                .thenReturn(false);

        Comentario resultado =
                publicacionService.actualizarComentario(99L, comentario);

        assertNull(resultado);

        verify(comentarioRepository, never()).save(any());
    }

    @Test
    void eliminarComentarioDebeEliminarPorId() {
        publicacionService.eliminarComentario(1L);

        verify(comentarioRepository).deleteById(1L);
    }

    // =========================
    // CALIFICACIONES
    // =========================

    @Test
    void obtenerCalificacionesDebeRetornarLista() {
        Calificacion calificacion =
                new Calificacion(1L, 1L, 5);

        when(calificacionRepository.findAll())
                .thenReturn(List.of(calificacion));

        List<Calificacion> resultado =
                publicacionService.obtenerCalificaciones();

        assertEquals(1, resultado.size());
        assertEquals(5, resultado.get(0).getPuntuacion());
    }

    @Test
    void obtenerCalificacionPorIdDebeRetornarCalificacion() {
        Calificacion calificacion =
                new Calificacion(1L, 1L, 5);

        when(calificacionRepository.findById(1L))
                .thenReturn(Optional.of(calificacion));

        Calificacion resultado =
                publicacionService.obtenerCalificacionPorId(1L);

        assertNotNull(resultado);
        assertEquals(5, resultado.getPuntuacion());
    }

    @Test
    void obtenerCalificacionPorIdInexistenteDebeRetornarNull() {
        when(calificacionRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertNull(publicacionService.obtenerCalificacionPorId(99L));
    }

    @Test
    void crearCalificacionDebeGuardarCalificacion() {
        Calificacion calificacion =
                new Calificacion(1L, 1L, 5);

        when(calificacionRepository.save(calificacion))
                .thenReturn(calificacion);

        Calificacion resultado =
                publicacionService.crearCalificacion(calificacion);

        assertNotNull(resultado);
        assertEquals(5, resultado.getPuntuacion());

        verify(calificacionRepository).save(calificacion);
    }

    @Test
    void actualizarCalificacionExistenteDebeGuardarCambios() {
        Calificacion calificacion =
                new Calificacion(null, 1L, 4);

        when(calificacionRepository.existsById(1L))
                .thenReturn(true);

        when(calificacionRepository.save(calificacion))
                .thenReturn(calificacion);

        Calificacion resultado =
                publicacionService.actualizarCalificacion(1L, calificacion);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(4, resultado.getPuntuacion());

        verify(calificacionRepository).save(calificacion);
    }

    @Test
    void actualizarCalificacionInexistenteDebeRetornarNull() {
        Calificacion calificacion =
                new Calificacion(null, 1L, 4);

        when(calificacionRepository.existsById(99L))
                .thenReturn(false);

        Calificacion resultado =
                publicacionService.actualizarCalificacion(99L, calificacion);

        assertNull(resultado);

        verify(calificacionRepository, never()).save(any());
    }

    @Test
    void eliminarCalificacionDebeEliminarPorId() {
        publicacionService.eliminarCalificacion(1L);

        verify(calificacionRepository).deleteById(1L);
    }

    // =========================
    // PROMEDIOS
    // =========================

    @Test
    void obtenerPromediosDebeCalcularPromedioPorPublicacion() {
        Publicacion publicacion1 =
                new Publicacion(1L, "Publicación 1", "Contenido", "Catalina");

        Publicacion publicacion2 =
                new Publicacion(2L, "Publicación 2", "Contenido", "Catalina");

        List<Calificacion> calificaciones = List.of(
                new Calificacion(1L, 1L, 5),
                new Calificacion(2L, 1L, 3),
                new Calificacion(3L, 2L, 4)
        );

        when(publicacionRepository.findAll())
                .thenReturn(List.of(publicacion1, publicacion2));

        when(calificacionRepository.findAll())
                .thenReturn(calificaciones);

        Map<String, Double> resultado =
                publicacionService.obtenerPromedios();

        assertEquals(4.0, resultado.get("publicacion1"));
        assertEquals(4.0, resultado.get("publicacion2"));
    }

    @Test
    void obtenerPromediosSinCalificacionesDebeRetornarCero() {
        Publicacion publicacion =
                new Publicacion(1L, "Sin calificaciones", "Contenido", "Catalina");

        when(publicacionRepository.findAll())
                .thenReturn(List.of(publicacion));

        when(calificacionRepository.findAll())
                .thenReturn(List.of());

        Map<String, Double> resultado =
                publicacionService.obtenerPromedios();

        assertEquals(0.0, resultado.get("publicacion1"));
    }
}