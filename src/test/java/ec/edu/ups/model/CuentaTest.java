package ec.edu.ups.model;

/**
 * @author AdrianC47
 * @date 29/12/24
 */
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
public class CuentaTest {

    @Test
    public void testConstructorAndGetters() {
        Cliente cliente = new Cliente(1L, "Juan Pérez", "0102030405", "0987654321", null);
        Cuenta cuenta = new Cuenta(1L, cliente, 1001, 1000.0);

        assertEquals(1L, cuenta.getCodigo());
        assertEquals(cliente, cuenta.getCliente());
        assertEquals(1001, cuenta.getNumeroCuenta());
        assertEquals(1000.0, cuenta.getSaldo());
    }

    @Test
    public void testSetters() {
        Cuenta cuenta = new Cuenta();
        Cliente cliente = new Cliente(2L, "María Gómez", "0203040506", "0987654322", null);

        cuenta.setCodigo(2L);
        cuenta.setCliente(cliente);
        cuenta.setNumeroCuenta(2001);
        cuenta.setSaldo(500.0);

        assertEquals(2L, cuenta.getCodigo());
        assertEquals(cliente, cuenta.getCliente());
        assertEquals(2001, cuenta.getNumeroCuenta());
        assertEquals(500.0, cuenta.getSaldo());
    }

    @Test
    public void testEqualsAndHashCode() {
        Cliente cliente = new Cliente(1L, "Juan Pérez", "0102030405", "0987654321", new ArrayList<>());
        Cuenta cuenta1 = new Cuenta(1L, cliente, 1001, 1000.0);
        Cuenta cuenta2 = new Cuenta(1L, cliente, 1001, 1000.0);
        Cuenta cuenta3 = new Cuenta(2L, cliente, 1002, 500.0);

        // Validar equals
        assertEquals(cuenta1, cuenta2, "Cuentas con el mismo código deben ser iguales");
        assertNotEquals(cuenta1, cuenta3, "Cuentas con diferentes códigos no deben ser iguales");

        // Validar hashCode
        assertEquals(cuenta1.hashCode(), cuenta2.hashCode(), "Cuentas con el mismo código deben tener el mismo hashCode");
        assertNotEquals(cuenta1.hashCode(), cuenta3.hashCode(), "Cuentas con diferentes códigos deben tener hashCodes diferentes");
    }

    @Test
    public void testToString() {
        Cliente cliente = new Cliente(1L, "Juan Pérez", "0102030405", "0987654321", null);
        Cuenta cuenta = new Cuenta(1L, cliente, 1001, 1000.0);
        String expected = "Cuenta(codigo=1, cliente=Cliente(id=1, nombre=Juan Pérez, cedula=0102030405, telefono=0987654321, listaCuentas=null), numeroCuenta=1001, saldo=1000.0)";

        assertEquals(expected, cuenta.toString(), "El método toString debe generar la representación esperada");
    }

}
