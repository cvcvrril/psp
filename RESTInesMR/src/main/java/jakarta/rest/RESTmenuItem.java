package jakarta.rest;

import dao.modelo.MenuItem;
import dao.modelo.errores.ErrorCMenuItem;
import domain.servicios.SERVmenuItems;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/menu_item")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RESTmenuItem {

    private final SERVmenuItems sev;

    @Inject
    public RESTmenuItem(SERVmenuItems sev) {
        this.sev = sev;
    }

    @GET
    public Response getAllMenuItems(){
        Either<ErrorCMenuItem, List<MenuItem>> result = sev.getAll();
        return Response.ok(result.get()).build();
    }

    @GET
    @Path("/{id}")
    public Response getIdMenuItem(@PathParam("id") Integer id){
        Either<ErrorCMenuItem, MenuItem> result = sev.get(id);
        return Response.ok(result.get()).build();
    }
}
