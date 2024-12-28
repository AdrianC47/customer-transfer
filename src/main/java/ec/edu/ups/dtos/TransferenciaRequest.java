package ec.edu.ups.dtos;

import lombok.Data;

/**
 * @author AdrianC47
 * @date 28/12/24
 */
@Data
public class TransferenciaRequest {
    private int cuentaOrigen;
    private int cuentaDestino;
    private Double monto;
    private String descripcion;
}
