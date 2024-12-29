package ec.edu.ups.controller;

import ec.edu.ups.dtos.TransferenciaRequest;
import ec.edu.ups.model.Cuenta;
import ec.edu.ups.model.Transaccion;
import ec.edu.ups.service.CuentaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * @author AdrianC47
 * @date 28/12/24
 */

@Path("/transferencias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class TransferController {

    private final CuentaService cuentaService;

    @Inject
    public TransferController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @POST
    @Path("/transferir")
    @Produces(MediaType.APPLICATION_JSON)
    public Response transferir(TransferenciaRequest transferenciaRequest) {
        // Validar monto inválido
        if (transferenciaRequest.getMonto() == null || transferenciaRequest.getMonto() <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Monto inválido")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }

        Cuenta origen = cuentaService.findByNumeroCuenta(transferenciaRequest.getCuentaOrigen());
        Cuenta destino = cuentaService.findByNumeroCuenta(transferenciaRequest.getCuentaDestino());

        if (origen == null || destino == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Cuenta no encontrada")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }

        // Validar transferencia entre la misma cuenta
        if (origen.getNumeroCuenta() == destino.getNumeroCuenta()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Transferencia entre la misma cuenta no permitida")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }

        Transaccion resultado = cuentaService.transferirDinero(origen, destino, transferenciaRequest.getMonto(), transferenciaRequest.getDescripcion());

        if (!resultado.getExito()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Fondos insuficientes")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }

        return Response.ok(resultado)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }




}
