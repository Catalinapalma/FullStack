package tienda.mascota.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tienda.mascota.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}