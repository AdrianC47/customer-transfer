package ec.edu.ups.repository;

import ec.edu.ups.model.Cuenta;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author AdrianC47
 * @date 28/12/24
 */
@QuarkusTest
public class CuentaRepositoryTest {

    private CuentaRepository cuentaRepository;
    @BeforeEach
    void  setUp() {
        cuentaRepository = new CuentaRepository();
    }

    @Test
    void testFindByNumeroCuenta() {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(1001);
        assertNotNull(cuenta);
        assertEquals(1001, cuenta.getNumeroCuenta());
    }


}
