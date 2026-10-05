package publicaciones.publicaciones.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class ComentarioTest {

    @Test
    void debeCrearComentarioCorrectamente() {

        Comentario comentario = new Comentario(
                1L,
                1L,
                "Catalina",
                "Excelente publicación"
        );

        assertNotNull(comentario);
        assertEquals(1L, comentario.getId());
        assertEquals(1L, comentario.getPublicacionId());
        assertEquals("Catalina", comentario.getAutor());
        assertEquals("Excelente publicación", comentario.getComentario());
    }

    @Test
    void debeModificarComentarioCorrectamente() {

        Comentario comentario = new Comentario();

        comentario.setId(2L);
        comentario.setPublicacionId(1L);
        comentario.setAutor("Catalina");
        comentario.setComentario("Comentario actualizado");

        assertEquals(2L, comentario.getId());
        assertEquals(1L, comentario.getPublicacionId());
        assertEquals("Catalina", comentario.getAutor());
        assertEquals("Comentario actualizado", comentario.getComentario());
    }
}