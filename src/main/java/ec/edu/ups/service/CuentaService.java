package ec.edu.ups.service;

import ec.edu.ups.model.Cuenta;
import ec.edu.ups.model.Transaccion;
import ec.edu.ups.repository.ClienteRepository;
import ec.edu.ups.repository.CuentaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;

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
    public Transaccion transferirDinero(Cuenta origen, Cuenta destino, Double monto, String descripcion) {
        if (origen.getSaldo() < monto) {
            return new Transaccion(null, origen, destino, monto, descripcion, false, LocalDateTime.now());
        }

        origen.setSaldo(origen.getSaldo() - monto);
        destino.setSaldo(destino.getSaldo() + monto);

        cuentaRepository.persist(origen);
        cuentaRepository.persist(destino);

        return new Transaccion(null, origen, destino, monto, descripcion, true, LocalDateTime.now());
    }


    public Cuenta findByNumeroCuenta(int numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta);
    }


}
