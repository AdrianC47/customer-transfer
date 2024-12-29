package ec.edu.ups.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AdrianC47
 * @date 29/12/24
 */
@QuarkusTest
public class ClienteTest {

    @Test
    public void testConstructorAndGetters() {
        Cliente cliente = new Cliente(1L, "Juan Pérez", "0102030405", "0987654321", new ArrayList<>());

        assertEquals(1L, cliente.getId());
        assertEquals("Juan Pérez", cliente.getNombre());
        assertEquals("0102030405", cliente.getCedula());
        assertEquals("0987654321", cliente.getTelefono());
        assertNotNull(cliente.getListaCuentas());
        assertTrue(cliente.getListaCuentas().isEmpty());
    }

    @Test
    public void testSetters() {
        Cliente cliente = new Cliente();
        cliente.setId(2L);
        cliente.setNombre("María Gómez");
        cliente.setCedula("0203040506");
        cliente.setTelefono("0987654322");
        cliente.setListaCuentas(new ArrayList<>());

        assertEquals(2L, cliente.getId());
        assertEquals("María Gómez", cliente.getNombre());
        assertEquals("0203040506", cliente.getCedula());
        assertEquals("0987654322", cliente.getTelefono());
        assertNotNull(cliente.getListaCuentas());
    }

    @Test
    public void testAddCuenta() {
        Cliente cliente = new Cliente(1L, "Juan Pérez", "0102030405", "0987654321", new ArrayList<>());
        Cuenta cuenta = new Cuenta(1L, cliente, 1001, 1000.0);

        cliente.getListaCuentas().add(cuenta);

        assertFalse(cliente.getListaCuentas().isEmpty());
        assertEquals(1, cliente.getListaCuentas().size());
        assertEquals(cuenta, cliente.getListaCuentas().get(0));
    }

    @Test
    public void testEqualsAndHashCode() {
        Cliente cliente1 = new Cliente(1L, "Juan Pérez", "0102030405", "0987654321", new ArrayList<>());
        Cliente cliente2 = new Cliente(1L, "Juan Pérez", "0102030405", "0987654321", new ArrayList<>());
        Cliente cliente3 = new Cliente(2L, "María Gómez", "0203040506", "0987654322", new ArrayList<>());

        // Validar equals
        assertEquals(cliente1, cliente2, "Clientes con el mismo ID deben ser iguales");
        assertNotEquals(cliente1, cliente3, "Clientes con diferentes IDs no deben ser iguales");

        // Validar hashCode
        assertEquals(cliente1.hashCode(), cliente2.hashCode(), "Clientes con el mismo ID deben tener el mismo hashCode");
        assertNotEquals(cliente1.hashCode(), cliente3.hashCode(), "Clientes con diferentes IDs deben tener hashCodes diferentes");
    }

    @Test
    public void testToString() {
        Cliente cliente = new Cliente(1L, "Juan Pérez", "0102030405", "0987654321", new ArrayList<>());
        String expected = "Cliente(id=1, nombre=Juan Pérez, cedula=0102030405, telefono=0987654321, listaCuentas=[])";

        assertEquals(expected, cliente.toString(), "El método toString debe generar la representación esperada");
    }

}