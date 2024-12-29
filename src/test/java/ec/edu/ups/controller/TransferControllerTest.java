package ec.edu.ups.controller;

import ec.edu.ups.dtos.TransferenciaRequest;
import ec.edu.ups.model.Cuenta;
import ec.edu.ups.model.Transaccion;
import ec.edu.ups.service.CuentaService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;

/**
 * @author AdrianC47
 * @date 29/12/24
 */

@QuarkusTest
public class TransferControllerTest {

    @InjectMock
    private CuentaService cuentaService;

    @Test
    public void testTransferenciaExitosaEndpoint() {
        // Configurar datos de prueba
        Cuenta origen = new Cuenta(1L, null, 1001, 1000.0);
        Cuenta destino = new Cuenta(2L, null, 1002, 500.0);
        Transaccion transaccion = new Transaccion(null, origen, destino, 500.0, "Pago", true, LocalDateTime.now());

        when(cuentaService.findByNumeroCuenta(1001)).thenReturn(origen);
        when(cuentaService.findByNumeroCuenta(1002)).thenReturn(destino);
        when(cuentaService.transferirDinero(origen, destino, 500.0, "Pago")).thenReturn(transaccion);

        // Simular la solicitud HTTP
        given()
                .body(new TransferenciaRequest(1001, 1002, 500.0, "Pago"))
                .contentType(ContentType.JSON)
                .when()
                .post("/transferencias/transferir")
                .then()
                .statusCode(200)
                .body("exito", is(true));
    }

    @Test
    public void testTransferenciaFondosInsuficientesEndpoint() {
        // Configurar datos de prueba
        Cuenta origen = new Cuenta(1L, null, 1001, 500.0);
        Cuenta destino = new Cuenta(2L, null, 1002, 500.0);
        Transaccion transaccion = new Transaccion(null, origen, destino, 700.0, "Pago fallido", false, LocalDateTime.now());

        when(cuentaService.findByNumeroCuenta(1001)).thenReturn(origen);
        when(cuentaService.findByNumeroCuenta(1002)).thenReturn(destino);
        when(cuentaService.transferirDinero(origen, destino, 700.0, "Pago fallido")).thenReturn(transaccion);

        // Simular la solicitud HTTP
        given()
                .body(new TransferenciaRequest(1001, 1002, 700.0, "Pago fallido"))
                .contentType(ContentType.JSON)
                .when()
                .post("/transferencias/transferir")
                .then()
                .statusCode(400) // Código esperado para error de negocio
                .body(is("Fondos insuficientes"));
    }

    @Test
    public void testTransferenciaCuentaNoEncontradaEndpoint() {
        // Configurar datos de prueba
        when(cuentaService.findByNumeroCuenta(9999)).thenReturn(null); // Cuenta no encontrada

        // Simular la solicitud HTTP
        given()
                .body(new TransferenciaRequest(9999, 1002, 500.0, "Pago"))
                .contentType(ContentType.JSON)
                .when()
                .post("/transferencias/transferir")
                .then()
                .statusCode(404) // Código esperado para recurso no encontrado
                .body(is("Cuenta no encontrada"));
    }

    @Test
    public void testTransferenciaMontoInvalidoEndpoint() {
        // Configurar datos de prueba
        Cuenta origen = new Cuenta(1L, null, 1001, 1000.0);
        Cuenta destino = new Cuenta(2L, null, 1002, 500.0);

        when(cuentaService.findByNumeroCuenta(1001)).thenReturn(origen);
        when(cuentaService.findByNumeroCuenta(1002)).thenReturn(destino);

        // Simular la solicitud HTTP con monto negativo
        given()
                .body(new TransferenciaRequest(1001, 1002, -500.0, "Monto inválido"))
                .contentType(ContentType.JSON)
                .when()
                .post("/transferencias/transferir")
                .then()
                .statusCode(400) // Código esperado para error de negocio
                .body(is("Monto inválido")); // Cambia el mensaje esperado
    }

    @Test
    public void testTransferenciaEntreMismaCuentaEndpoint() {
        // Configurar datos de prueba
        Cuenta origen = new Cuenta(1L, null, 1001, 1000.0);

        when(cuentaService.findByNumeroCuenta(1001)).thenReturn(origen);

        // Simular la solicitud HTTP
        given()
                .body(new TransferenciaRequest(1001, 1001, 500.0, "Misma cuenta"))
                .contentType(ContentType.JSON)
                .when()
                .post("/transferencias/transferir")
                .then()
                .statusCode(400) // Código esperado para error de negocio
                .body(is("Transferencia entre la misma cuenta no permitida"));
    }

}
