package ec.edu.ups.dtos;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AdrianC47
 * @date 29/12/24
 */
@QuarkusTest
public class TransferenciaRequestTest {
    @Test
    public void testEqualsAndHashCode() {
        TransferenciaRequest request1 = new TransferenciaRequest(1001, 1002, 500.0, "Pago");
        TransferenciaRequest request2 = new TransferenciaRequest(1001, 1002, 500.0, "Pago");
        TransferenciaRequest request3 = new TransferenciaRequest(1003, 1004, 700.0, "Otro Pago");

        // Validar equals
        assertEquals(request1, request2, "Requests con los mismos datos deben ser iguales");
        assertNotEquals(request1, request3, "Requests con datos diferentes no deben ser iguales");

        // Validar hashCode
        assertEquals(request1.hashCode(), request2.hashCode(), "Requests con los mismos datos deben tener el mismo hashCode");
        assertNotEquals(request1.hashCode(), request3.hashCode(), "Requests con datos diferentes deben tener hashCodes diferentes");
    }

    @Test
    public void testToString() {
        TransferenciaRequest request = new TransferenciaRequest(1001, 1002, 500.0, "Pago");
        String expected = "TransferenciaRequest(cuentaOrigen=1001, cuentaDestino=1002, monto=500.0, descripcion=Pago)";

        assertEquals(expected, request.toString(), "El método toString debe generar la representación esperada");
    }
}
