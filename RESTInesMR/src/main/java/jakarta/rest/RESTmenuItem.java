package jakarta.rest;

import dao.ConstantsDAO;
import dao.modelo.MenuItem;
import domain.modelo.excepciones.BadArgumentException;
import domain.servicios.SERVmenuItems;
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
@Path(ConstantsJakarta.MENU_ITEM)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RESTmenuItem {

    private final SERVmenuItems sev;

    @Inject
    public RESTmenuItem(SERVmenuItems sev) {
        this.sev = sev;
    }

    @GET
    public Response getAllMenuItems() {
        Either<ApiError, List<MenuItem>> result = sev.getAll();
        if (result.isRight()) {
            return Response.ok(result.get()).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result.getLeft()).build();
        }
    }

    @GET
    @Path(ConstantsJakarta.ID_PATH)

    public Response getIdMenuItem(@PathParam(ConstantsJakarta.ID) String idParam) {
        Either<ApiError, MenuItem> result = sev.get(idParam);
        if (result.isRight()) {
            return Response.ok(result.get()).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result.getLeft()).build();
        }
    }

}
