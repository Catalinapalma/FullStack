package publicaciones.publicaciones.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PUBLICACION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publicacion {

    @Id
    private Long id;

    private String titulo;
    private String contenido;
    private String autor;
}