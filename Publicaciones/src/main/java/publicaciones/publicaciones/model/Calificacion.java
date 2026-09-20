package publicaciones.publicaciones.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CALIFICACION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calificacion {

    @Id
    private Long id;

    private Long publicacionId;
    private int puntuacion;
}