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
    public Response transferir(TransferenciaRequest transferenciaRequest) {


        return Response.ok(null).build();
    }

}
