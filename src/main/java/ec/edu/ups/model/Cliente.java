package ec.edu.ups.model;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author AdrianC47
 * @date 26/12/24
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends PanacheEntityBase {

    @Id
    private int id;
    private String nombre;
    private String cedula;
    private String telefono;
    @OneToMany(mappedBy = "otraEntidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cuenta> listaCuentas;


}
