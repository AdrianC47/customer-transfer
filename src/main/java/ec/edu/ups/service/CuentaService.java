package ec.edu.ups.service;

import ec.edu.ups.repository.ClienteRepository;
import ec.edu.ups.repository.CuentaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * @author AdrianC47
 * @date 27/12/24
 */
@ApplicationScoped
public class CuentaService {

    private final CuentaRepository  cuentaRepository;

    @Inject
    public CuentaService(CuentaRepository cuentaRepository){
        this.cuentaRepository   = cuentaRepository;
    }

}
