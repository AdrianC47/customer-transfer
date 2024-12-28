package ec.edu.ups.repository;


import ec.edu.ups.model.Cuenta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CuentaRepository implements PanacheRepository <Cuenta> {
}
