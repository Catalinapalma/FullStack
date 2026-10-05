package publicaciones.publicaciones.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class CalificacionTest {

    @Test
    void debeCrearCalificacionCorrectamente() {

        Calificacion calificacion = new Calificacion(
                1L,
                1L,
                5
        );

        assertNotNull(calificacion);
        assertEquals(1L, calificacion.getId());
        assertEquals(1L, calificacion.getPublicacionId());
        assertEquals(5, calificacion.getPuntuacion());
    }

    @Test
    void debeModificarCalificacionCorrectamente() {

        Calificacion calificacion = new Calificacion();

        calificacion.setId(2L);
        calificacion.setPublicacionId(1L);
        calificacion.setPuntuacion(4);

        assertEquals(2L, calificacion.getId());
        assertEquals(1L, calificacion.getPublicacionId());
        assertEquals(4, calificacion.getPuntuacion());
    }
}