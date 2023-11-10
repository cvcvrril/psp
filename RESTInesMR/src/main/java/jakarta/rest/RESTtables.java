package jakarta.rest;

import dao.modelo.TableRestaurant;
import dao.modelo.errores.ErrorCTables;
import domain.servicios.SERVtablesRestaurant;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
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
        if (result.isRight()){
            return Response.ok(result.get()).build();
        } else {
//            return Response.status(Response.Status.BAD_GATEWAY).build();
            throw new RuntimeException();
        }
    }

    @GET
    @Path("/{id}")
    public Response getIdTable(@PathParam("id") Integer id){
        Either<ErrorCTables, TableRestaurant> result = serv.get(id);
        return Response.ok(result.get()).build();
    }

}
