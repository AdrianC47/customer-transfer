package ec.edu.ups.repository;
import ec.edu.ups.model.Cliente;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.InjectMock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AdrianC47
 * @date 29/12/24
 */
@QuarkusTest
public class ClienteRepositoryTest {
    @InjectMock
    private ClienteRepository clienteRepository;

    @Test
    public void testListAllClientes() {
        Cliente cliente1 = new Cliente(1L, "Juan Pérez", "0102030405", "0987654321", null);
        Cliente cliente2 = new Cliente(2L, "María Gómez", "0203040506", "0987654322", null);

        when(clienteRepository.listAll()).thenReturn(List.of(cliente1, cliente2));

        var clientes = clienteRepository.listAll();

        assertNotNull(clientes, "La lista de clientes no debe ser nula");
        assertEquals(2, clientes.size(), "Debe devolver exactamente 2 clientes");
        verify(clienteRepository, times(1)).listAll();
    }
}