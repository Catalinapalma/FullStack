package publicaciones.publicaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import publicaciones.publicaciones.model.Publicacion;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
}