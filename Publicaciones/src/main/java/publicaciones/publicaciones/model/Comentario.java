package publicaciones.publicaciones.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COMENTARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comentario {

    @Id
    private Long id;

    private Long publicacionId;
    private String autor;
    private String comentario;
}