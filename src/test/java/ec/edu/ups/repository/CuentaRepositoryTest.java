package ec.edu.ups.repository;

import ec.edu.ups.model.Cuenta;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.InjectMock;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author AdrianC47
 * @date 29/12/24
 */

@QuarkusTest
public class CuentaRepositoryTest {
    @InjectMock
    private CuentaRepository cuentaRepository;

    @Test
    public void testFindByNumeroCuenta() {
        Cuenta cuenta = new Cuenta(1L, null, 1001, 1000.0);

        when(cuentaRepository.findByNumeroCuenta(1001)).thenReturn(cuenta);

        Cuenta result = cuentaRepository.findByNumeroCuenta(1001);

        assertNotNull(result, "La cuenta encontrada no debe ser nula");
        assertEquals(1001, result.getNumeroCuenta(), "El número de cuenta debe coincidir");
        verify(cuentaRepository, times(1)).findByNumeroCuenta(1001);
    }
}