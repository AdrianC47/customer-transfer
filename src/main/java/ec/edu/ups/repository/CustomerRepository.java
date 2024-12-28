package ec.edu.ups.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * @author AdrianC47
 * @date 27/12/24
 */

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
}
