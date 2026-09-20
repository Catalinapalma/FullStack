package tienda.mascota.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    private Long id;

    private String nombre;
    private String categoria;
    private double precio;
    private double costo;
}