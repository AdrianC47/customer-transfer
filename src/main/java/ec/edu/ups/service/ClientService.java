package ec.edu.ups.service;

import ec.edu.ups.model.Cliente;
import ec.edu.ups.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

/**
 * @author AdrianC47
 * @date 27/12/24
 */
@ApplicationScoped
public class ClientService {

    private final ClienteRepository clienteRepository;

    @Inject
    public ClientService (ClienteRepository clienteRepository){
        this.clienteRepository  = clienteRepository;
    }

    public List<Cliente> listAll() {
        return clienteRepository.listAll();
    }
}
