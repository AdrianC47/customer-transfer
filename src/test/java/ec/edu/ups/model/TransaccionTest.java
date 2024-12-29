package ec.edu.ups.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author AdrianC47
 * @date 29/12/24
 */
@QuarkusTest
public class TransaccionTest {

    @Test
    public void testConstructorAndGetters() {
        Cuenta origen = new Cuenta(1L, null, 1001, 1000.0);
        Cuenta destino = new Cuenta(2L, null, 1002, 500.0);
        LocalDateTime fecha = LocalDateTime.now();

        Transaccion transaccion = new Transaccion(1L, origen, destino, 500.0, "Pago exitoso", true, fecha);

        assertEquals(1L, transaccion.getId());
        assertEquals(origen, transaccion.getCuentaOrigen());
        assertEquals(destino, transaccion.getCuentaDestino());
        assertEquals(500.0, transaccion.getMonto());
        assertEquals("Pago exitoso", transaccion.getDescripcion());
        assertTrue(transaccion.getExito());
        assertEquals(fecha, transaccion.getFecha());
    }

    @Test
    public void testSetters() {
        Transaccion transaccion = new Transaccion();
        Cuenta origen = new Cuenta(1L, null, 1001, 1000.0);
        Cuenta destino = new Cuenta(2L, null, 1002, 500.0);
        LocalDateTime fecha = LocalDateTime.now();

        transaccion.setId(2L);
        transaccion.setCuentaOrigen(origen);
        transaccion.setCuentaDestino(destino);
        transaccion.setMonto(700.0);
        transaccion.setDescripcion("Pago fallido");
        transaccion.setExito(false);
        transaccion.setFecha(fecha);

        assertEquals(2L, transaccion.getId());
        assertEquals(origen, transaccion.getCuentaOrigen());
        assertEquals(destino, transaccion.getCuentaDestino());
        assertEquals(700.0, transaccion.getMonto());
        assertEquals("Pago fallido", transaccion.getDescripcion());
        assertFalse(transaccion.getExito());
        assertEquals(fecha, transaccion.getFecha());
    }

    @Test
    public void testEqualsAndHashCode() {
        Cuenta origen = new Cuenta(1L, null, 1001, 1000.0);
        Cuenta destino = new Cuenta(2L, null, 1002, 500.0);
        LocalDateTime fecha = LocalDateTime.now();

        Transaccion transaccion1 = new Transaccion(1L, origen, destino, 500.0, "Pago exitoso", true, fecha);
        Transaccion transaccion2 = new Transaccion(1L, origen, destino, 500.0, "Pago exitoso", true, fecha);
        Transaccion transaccion3 = new Transaccion(2L, origen, destino, 700.0, "Pago fallido", false, fecha);

        // Validar equals
        assertEquals(transaccion1, transaccion2, "Transacciones con el mismo ID deben ser iguales");
        assertNotEquals(transaccion1, transaccion3, "Transacciones con diferentes IDs no deben ser iguales");

        // Validar hashCode
        assertEquals(transaccion1.hashCode(), transaccion2.hashCode(), "Transacciones con el mismo ID deben tener el mismo hashCode");
        assertNotEquals(transaccion1.hashCode(), transaccion3.hashCode(), "Transacciones con diferentes IDs deben tener hashCodes diferentes");
    }

    @Test
    public void testToString() {
        Cuenta origen = new Cuenta(1L, null, 1001, 1000.0);
        Cuenta destino = new Cuenta(2L, null, 1002, 500.0);
        LocalDateTime fecha = LocalDateTime.now();

        Transaccion transaccion = new Transaccion(1L, origen, destino, 500.0, "Pago exitoso", true, fecha);
        String expected = "Transaccion(id=1, cuentaOrigen=Cuenta(codigo=1, cliente=null, numeroCuenta=1001, saldo=1000.0), cuentaDestino=Cuenta(codigo=2, cliente=null, numeroCuenta=1002, saldo=500.0), monto=500.0, descripcion=Pago exitoso, exito=true, fecha=" + fecha + ")";

        assertEquals(expected, transaccion.toString(), "El método toString debe generar la representación esperada");
    }

}
