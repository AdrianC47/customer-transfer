package ec.edu.ups.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private int codigo;
    private int numeroCuenta;
    private Double saldo;



}
