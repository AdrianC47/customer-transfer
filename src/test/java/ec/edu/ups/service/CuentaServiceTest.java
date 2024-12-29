package ec.edu.ups.service;

import ec.edu.ups.model.Cuenta;
import ec.edu.ups.model.Transaccion;
import ec.edu.ups.repository.CuentaRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author AdrianC47
 * @date 29/12/24
 */

@QuarkusTest
public class CuentaServiceTest {

    @Inject
    private CuentaService cuentaService;


    @InjectMock
    private CuentaRepository cuentaRepository; // Mock del repositorio

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializar mocks de Mockito
        cuentaService = new CuentaService(cuentaRepository); // Injectar el mock en el servicio
    }

    @Test
    public void testTransferenciaExitosa() {
        // Configurar datos de prueba
        Cuenta origen = new Cuenta(1L, null, 1001, 1000.0);
        Cuenta destino = new Cuenta(2L, null, 1002, 500.0);

        when(cuentaRepository.findByNumeroCuenta(1001)).thenReturn(origen);
        when(cuentaRepository.findByNumeroCuenta(1002)).thenReturn(destino);

        // Llamar al metodo
        Transaccion transaccion = cuentaService.transferirDinero(origen, destino, 500.0, "Pago de servicios");

        // Validaciones
        assertNotNull(transaccion, "La transacción no debe ser nula");
        assertTrue(transaccion.getExito(), "La transacción debe ser exitosa");
        assertEquals(500.0, origen.getSaldo(), "El saldo de origen debe ser 500");
        assertEquals(1000.0, destino.getSaldo(), "El saldo de destino debe ser 1000");
    }

    @Test
    public void testTransferenciaFallidaPorFondosInsuficientes() {
        // Configurar datos de prueba
        Cuenta origen = new Cuenta(1L, null, 1001, 500.0);
        Cuenta destino = new Cuenta(2L, null, 1002, 500.0);

        when(cuentaRepository.findByNumeroCuenta(1001)).thenReturn(origen);
        when(cuentaRepository.findByNumeroCuenta(1002)).thenReturn(destino);


        Transaccion transaccion = cuentaService.transferirDinero(origen, destino, 700.0, "Pago fallido");

        // Validaciones
        assertNotNull(transaccion, "La transacción no debe ser nula");
        assertFalse(transaccion.getExito(), "La transacción debe fallar");
        assertEquals(500.0, origen.getSaldo(), "El saldo de origen no debe cambiar");
        assertEquals(500.0, destino.getSaldo(), "El saldo de destino no debe cambiar");
    }

    @Test
    public void testTransferenciaMontoNegativo() {
        // Configurar datos de prueba
        Cuenta origen = new Cuenta(1L, null, 1001, 1000.0);
        Cuenta destino = new Cuenta(2L, null, 1002, 500.0);

        when(cuentaRepository.findByNumeroCuenta(1001)).thenReturn(origen);
        when(cuentaRepository.findByNumeroCuenta(1002)).thenReturn(destino);

        // Llamar al metodo
        Transaccion transaccion = cuentaService.transferirDinero(origen, destino, -100.0, "Monto negativo");

        // Validaciones
        assertNotNull(transaccion, "La transacción no debe ser nula");
        assertFalse(transaccion.getExito(), "La transacción debe fallar");
        assertEquals(1000.0, origen.getSaldo(), "El saldo de origen no debe cambiar");
        assertEquals(500.0, destino.getSaldo(), "El saldo de destino no debe cambiar");
    }

    @Test
    public void testTransferenciaMontoCero() {
        // Configurar datos de prueba
        Cuenta origen = new Cuenta(1L, null, 1001, 1000.0);
        Cuenta destino = new Cuenta(2L, null, 1002, 500.0);

        when(cuentaRepository.findByNumeroCuenta(1001)).thenReturn(origen);
        when(cuentaRepository.findByNumeroCuenta(1002)).thenReturn(destino);

        // Llamar al metodo
        Transaccion transaccion = cuentaService.transferirDinero(origen, destino, 0.0, "Monto cero");

        // Validaciones
        assertNotNull(transaccion, "La transacción no debe ser nula");
        assertFalse(transaccion.getExito(), "La transacción debe fallar");
        assertEquals(1000.0, origen.getSaldo(), "El saldo de origen no debe cambiar");
        assertEquals(500.0, destino.getSaldo(), "El saldo de destino no debe cambiar");
    }

    @Test
    public void testTransferenciaEntreMismaCuenta() {
        // Configurar datos de prueba
        Cuenta origen = new Cuenta(1L, null, 1001, 1000.0);

        when(cuentaRepository.findByNumeroCuenta(1001)).thenReturn(origen);

        // Llamar al metodo
        Transaccion transaccion = cuentaService.transferirDinero(origen, origen, 500.0, "Misma cuenta");

        // Validaciones
        assertNotNull(transaccion, "La transacción no debe ser nula");
        assertFalse(transaccion.getExito(), "La transacción debe fallar");
        assertEquals(1000.0, origen.getSaldo(), "El saldo no debe cambiar");
    }

}
