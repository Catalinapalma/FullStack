package publicaciones.publicaciones.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import publicaciones.publicaciones.model.Calificacion;
import publicaciones.publicaciones.model.Comentario;
import publicaciones.publicaciones.model.Publicacion;
import publicaciones.publicaciones.repository.CalificacionRepository;
import publicaciones.publicaciones.repository.ComentarioRepository;
import publicaciones.publicaciones.repository.PublicacionRepository;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final ComentarioRepository comentarioRepository;
    private final CalificacionRepository calificacionRepository;

    public PublicacionServiceImpl(
            PublicacionRepository publicacionRepository,
            ComentarioRepository comentarioRepository,
            CalificacionRepository calificacionRepository) {

        this.publicacionRepository = publicacionRepository;
        this.comentarioRepository = comentarioRepository;
        this.calificacionRepository = calificacionRepository;
    }

    // PUBLICACIONES

    @Override
    public List<Publicacion> obtenerPublicaciones() {
        return publicacionRepository.findAll();
    }

    @Override
    public Publicacion obtenerPublicacionPorId(Long id) {
        return publicacionRepository.findById(id).orElse(null);
    }

    @Override
    public Publicacion crearPublicacion(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    @Override
    public Publicacion actualizarPublicacion(Long id, Publicacion publicacion) {

        if (publicacionRepository.existsById(id)) {
            publicacion.setId(id);
            return publicacionRepository.save(publicacion);
        }

        return null;
    }

    @Override
    public void eliminarPublicacion(Long id) {
        publicacionRepository.deleteById(id);
    }


    // COMENTARIOS

    @Override
    public List<Comentario> obtenerComentarios() {
        return comentarioRepository.findAll();
    }

    @Override
    public Comentario obtenerComentarioPorId(Long id) {
        return comentarioRepository.findById(id).orElse(null);
    }

    @Override
    public Comentario crearComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Override
    public Comentario actualizarComentario(Long id, Comentario comentario) {

        if (comentarioRepository.existsById(id)) {
            comentario.setId(id);
            return comentarioRepository.save(comentario);
        }

        return null;
    }

    @Override
    public void eliminarComentario(Long id) {
        comentarioRepository.deleteById(id);
    }


    // CALIFICACIONES

    @Override
    public List<Calificacion> obtenerCalificaciones() {
        return calificacionRepository.findAll();
    }

    @Override
    public Calificacion obtenerCalificacionPorId(Long id) {
        return calificacionRepository.findById(id).orElse(null);
    }

    @Override
    public Calificacion crearCalificacion(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    @Override
    public Calificacion actualizarCalificacion(Long id, Calificacion calificacion) {

        if (calificacionRepository.existsById(id)) {
            calificacion.setId(id);
            return calificacionRepository.save(calificacion);
        }

        return null;
    }

    @Override
    public void eliminarCalificacion(Long id) {
        calificacionRepository.deleteById(id);
    }


    // PROMEDIOS

    @Override
    public Map<String, Double> obtenerPromedios() {

        List<Publicacion> publicaciones = publicacionRepository.findAll();
        List<Calificacion> calificaciones = calificacionRepository.findAll();

        Map<String, Double> promedios = new LinkedHashMap<>();

        for (Publicacion publicacion : publicaciones) {

            double promedio = calificaciones.stream()
                    .filter(c -> c.getPublicacionId().equals(publicacion.getId()))
                    .mapToInt(Calificacion::getPuntuacion)
                    .average()
                    .orElse(0.0);

            promedios.put(
                    "publicacion" + publicacion.getId(),
                    promedio
            );
        }

        return promedios;
    }
}