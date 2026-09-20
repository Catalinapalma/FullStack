package publicaciones.publicaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import publicaciones.publicaciones.model.Calificacion;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
}