package tienda.mascota.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tienda.mascota.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}