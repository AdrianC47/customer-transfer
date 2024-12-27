package ec.edu.ups.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author AdrianC47
 * @date 26/12/24
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private int id;
    private String nombre;
    private String cedula;
    private String telefono;
    private String correo;
    private String password;
}
