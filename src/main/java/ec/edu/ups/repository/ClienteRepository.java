package ec.edu.ups.repository;

import ec.edu.ups.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * @author AdrianC47
 * @date 27/12/24
 */
@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

}
