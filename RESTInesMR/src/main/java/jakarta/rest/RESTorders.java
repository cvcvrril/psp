package jakarta.rest;

import dao.ConstantsDAO;
import dao.modelo.Order;
import domain.modelo.excepciones.BadArgumentException;
import domain.servicios.SERVorder;
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
@Path(ConstantsJakarta.ORDERS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RESTorders {

    private final SERVorder serv;

    @Inject
    public RESTorders(SERVorder serv) {
        this.serv = serv;
    }

    @GET
    public Response getAllOrder() {
        Either<ApiError, List<Order>> result = serv.getAll();
        if (result.isRight()) {
            return Response.ok(result.get()).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result.getLeft()).build();
        }
    }

    @GET
    @Path(ConstantsJakarta.ID_PATH)
    public Response getIdOrder(@PathParam(ConstantsJakarta.ID) String idParam) {
        try {
            Integer id = Integer.parseInt(idParam);
            Either<ApiError, Order> result = serv.getOrder(id);
            if (result.isRight()) {
                return Response.ok(result.get()).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result.getLeft()).build();
            }
        } catch (NumberFormatException e) {
            log.error(e.getMessage(),e);
            throw new BadArgumentException(ConstantsDAO.BAD_ARGUMENT_EXCEPTION);
        }
    }

    @POST
    public Response addOrder(Order newOrder) {
        Either<ApiError, Integer> result = serv.add(newOrder);
        if (result.isRight()) {
            return Response.status(Response.Status.CREATED).entity(result.get()).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result.getLeft()).build();
        }
    }

    @PUT
    public Response updateOrder(Order updatedOrder) {
        Either<ApiError, Integer> result = serv.updateOrder(updatedOrder);
        if (result.isRight()) {
            return Response.ok(result.get()).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result.getLeft()).build();
        }
    }

    @DELETE
    @Path(ConstantsJakarta.ID_PATH)
    public Response deleteOrder(@PathParam(ConstantsJakarta.ID) String idParam) {
        try {
            Integer id = Integer.parseInt(idParam);
            Either<ApiError, Integer> result = serv.delOrder(id);
            if (result.isRight()) {
                return Response.ok(result.get()).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } catch (NumberFormatException e) {
            log.error(e.getMessage(),e);
            throw new BadArgumentException(ConstantsDAO.BAD_ARGUMENT_EXCEPTION);
        }
    }
}
