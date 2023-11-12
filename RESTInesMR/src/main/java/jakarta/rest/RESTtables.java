package jakarta.rest;

import dao.ConstantsDAO;
import dao.modelo.TableRestaurant;
import domain.modelo.excepciones.BadArgumentException;
import domain.servicios.SERVtablesRestaurant;
import io.vavr.control.Either;
import jakarta.ConstantsJakarta;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@Path(ConstantsJakarta.TABLES)
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
        Either<ApiError, List<TableRestaurant>> result = serv.getAll();
        if (result.isRight()) {
            return Response.ok(result.get()).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result.getLeft()).build();
        }
    }

    @GET
    @Path(ConstantsJakarta.ID_PATH)
    public Response getIdTable(@PathParam(ConstantsJakarta.ID) String idParam) {
        try {
            Integer id = Integer.parseInt(idParam);
            Either<ApiError, TableRestaurant> result = serv.get(id);
            if (result.isRight()) {
                return Response.ok(result.get()).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result.getLeft()).build();
            }
        }catch (NumberFormatException e){
            log.error(e.getMessage(),e);
            throw new BadArgumentException(ConstantsDAO.BAD_ARGUMENT_EXCEPTION);
        }
    }
}
