package ec.edu.ups.controller;

import ec.edu.ups.model.Cliente;
import ec.edu.ups.service.ClientService;
import ec.edu.ups.service.CuentaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author AdrianC47
 * @date 27/12/24
 */

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class ClientController {
    private final ClientService clientService;
    private final CuentaService cuentaService;

    @Inject
    public ClientController (ClientService clientService, CuentaService cuentaService){
        this.clientService = clientService;
        this.cuentaService = cuentaService;
    }

    @POST
    @Path("/list-all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> listAllClients() {
        return clientService.listAll();
    }

}
