package ec.edu.ups.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author AdrianC47
 * @date 28/12/24
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaccion extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cuenta_origen_id", nullable = false)
    private Cuenta cuentaOrigen;

    @ManyToOne
    @JoinColumn(name = "cuenta_destino_id", nullable = false)
    private Cuenta cuentaDestino;

    private Double monto;

    private String descripcion;

    private Boolean exito; // True si la transacción fue exitosa

    private LocalDateTime fecha;
}
