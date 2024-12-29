package ec.edu.ups.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author AdrianC47
 * @date 28/12/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaRequest {
    private int cuentaOrigen;
    private int cuentaDestino;
    private Double monto;
    private String descripcion;
}
