package publicaciones.publicaciones.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class PublicacionTest {

    @Test
    void debeCrearPublicacionCorrectamente() {

        Publicacion publicacion = new Publicacion(
                1L,
                "Docker",
                "Contenido de prueba",
                "Catalina"
        );

        assertNotNull(publicacion);
        assertEquals(1L, publicacion.getId());
        assertEquals("Docker", publicacion.getTitulo());
        assertEquals("Contenido de prueba", publicacion.getContenido());
        assertEquals("Catalina", publicacion.getAutor());
    }

    @Test
    void debeModificarPublicacionCorrectamente() {

        Publicacion publicacion = new Publicacion();

        publicacion.setId(2L);
        publicacion.setTitulo("Spring Boot");
        publicacion.setContenido("Microservicios");
        publicacion.setAutor("Catalina");

        assertEquals(2L, publicacion.getId());
        assertEquals("Spring Boot", publicacion.getTitulo());
        assertEquals("Microservicios", publicacion.getContenido());
        assertEquals("Catalina", publicacion.getAutor());
    }
}