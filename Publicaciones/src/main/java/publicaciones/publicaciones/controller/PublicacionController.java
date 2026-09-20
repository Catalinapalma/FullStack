package publicaciones.publicaciones.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import publicaciones.publicaciones.model.Calificacion;
import publicaciones.publicaciones.model.Comentario;
import publicaciones.publicaciones.model.Publicacion;
import publicaciones.publicaciones.service.PublicacionService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PublicacionController {

    private final PublicacionService publicacionService;


    // PUBLICACIONES

    @GetMapping("/publicaciones")
    public List<Publicacion> obtenerPublicaciones() {
        return publicacionService.obtenerPublicaciones();
    }

    @GetMapping("/publicaciones/{id}")
    public Publicacion obtenerPublicacionPorId(@PathVariable Long id) {
        return publicacionService.obtenerPublicacionPorId(id);
    }

    @PostMapping("/publicaciones")
    public Publicacion crearPublicacion(@RequestBody Publicacion publicacion) {
        return publicacionService.crearPublicacion(publicacion);
    }

    @PutMapping("/publicaciones/{id}")
    public Publicacion actualizarPublicacion(
            @PathVariable Long id,
            @RequestBody Publicacion publicacion) {

        return publicacionService.actualizarPublicacion(id, publicacion);
    }

    @DeleteMapping("/publicaciones/{id}")
    public void eliminarPublicacion(@PathVariable Long id) {
        publicacionService.eliminarPublicacion(id);
    }


    // COMENTARIOS

    @GetMapping("/comentarios")
    public List<Comentario> obtenerComentarios() {
        return publicacionService.obtenerComentarios();
    }

    @GetMapping("/comentarios/{id}")
    public Comentario obtenerComentarioPorId(@PathVariable Long id) {
        return publicacionService.obtenerComentarioPorId(id);
    }

    @PostMapping("/comentarios")
    public Comentario crearComentario(@RequestBody Comentario comentario) {
        return publicacionService.crearComentario(comentario);
    }

    @PutMapping("/comentarios/{id}")
    public Comentario actualizarComentario(
            @PathVariable Long id,
            @RequestBody Comentario comentario) {

        return publicacionService.actualizarComentario(id, comentario);
    }

    @DeleteMapping("/comentarios/{id}")
    public void eliminarComentario(@PathVariable Long id) {
        publicacionService.eliminarComentario(id);
    }


    // CALIFICACIONES

    @GetMapping("/calificaciones")
    public List<Calificacion> obtenerCalificaciones() {
        return publicacionService.obtenerCalificaciones();
    }

    @GetMapping("/calificaciones/{id}")
    public Calificacion obtenerCalificacionPorId(@PathVariable Long id) {
        return publicacionService.obtenerCalificacionPorId(id);
    }

    @PostMapping("/calificaciones")
    public Calificacion crearCalificacion(@RequestBody Calificacion calificacion) {
        return publicacionService.crearCalificacion(calificacion);
    }

    @PutMapping("/calificaciones/{id}")
    public Calificacion actualizarCalificacion(
            @PathVariable Long id,
            @RequestBody Calificacion calificacion) {

        return publicacionService.actualizarCalificacion(id, calificacion);
    }

    @DeleteMapping("/calificaciones/{id}")
    public void eliminarCalificacion(@PathVariable Long id) {
        publicacionService.eliminarCalificacion(id);
    }


    // PROMEDIOS

    @GetMapping("/promedios")
    public Map<String, Double> obtenerPromedios() {
        return publicacionService.obtenerPromedios();
    }
}