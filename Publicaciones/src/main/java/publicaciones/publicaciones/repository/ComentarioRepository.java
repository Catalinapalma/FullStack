package publicaciones.publicaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import publicaciones.publicaciones.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}