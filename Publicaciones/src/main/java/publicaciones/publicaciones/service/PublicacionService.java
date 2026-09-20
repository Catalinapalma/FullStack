package publicaciones.publicaciones.service;

import java.util.List;
import java.util.Map;

import publicaciones.publicaciones.model.Calificacion;
import publicaciones.publicaciones.model.Comentario;
import publicaciones.publicaciones.model.Publicacion;

public interface PublicacionService {

    // Publicaciones
    List<Publicacion> obtenerPublicaciones();
    Publicacion obtenerPublicacionPorId(Long id);
    Publicacion crearPublicacion(Publicacion publicacion);
    Publicacion actualizarPublicacion(Long id, Publicacion publicacion);
    void eliminarPublicacion(Long id);

    // Comentarios
    List<Comentario> obtenerComentarios();
    Comentario obtenerComentarioPorId(Long id);
    Comentario crearComentario(Comentario comentario);
    Comentario actualizarComentario(Long id, Comentario comentario);
    void eliminarComentario(Long id);

    // Calificaciones
    List<Calificacion> obtenerCalificaciones();
    Calificacion obtenerCalificacionPorId(Long id);
    Calificacion crearCalificacion(Calificacion calificacion);
    Calificacion actualizarCalificacion(Long id, Calificacion calificacion);
    void eliminarCalificacion(Long id);

    // Promedios
    Map<String, Double> obtenerPromedios();
}