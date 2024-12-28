package ec.edu.ups.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author AdrianC47
 * @date 27/12/24
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta  extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo; // Considera usar Long en lugar de int

    @ManyToOne // Esta anotación indica la relación con Cliente
    @JoinColumn(name = "cliente_id") // Nombre de la clave foránea en la base de datos
    private Cliente cliente;

    private int numeroCuenta;
    private Double saldo;
}