package tienda.mascota.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VENTA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_ID", nullable = false)
    private Producto producto;

    private int cantidad;
    private LocalDate fecha;
}