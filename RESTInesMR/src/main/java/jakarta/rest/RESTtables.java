package jakarta.rest;

import dao.modelo.TableRestaurant;
import dao.modelo.errores.ErrorCTables;
import domain.servicios.SERVtablesRestaurant;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/tables")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RESTtables {

    private final SERVtablesRestaurant serv;

    @Inject
    public RESTtables(SERVtablesRestaurant serv) {
        this.serv = serv;
    }

    @GET
    public Response getAllTable() {
        Either<ErrorCTables, List<TableRestaurant>> result = serv.getAll();
        return Response.ok(result.get()).build();
    }

    @GET
    @Path("/{id}")
    public Response getIdTable(@PathParam("id") Integer id){
        Either<ErrorCTables, TableRestaurant> result = serv.get(id);
        return Response.ok(result.get()).build();
    }

}
