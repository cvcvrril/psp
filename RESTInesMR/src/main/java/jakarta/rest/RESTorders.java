package jakarta.rest;

import dao.modelo.Order;
import domain.servicios.SERVorder;
import io.vavr.control.Either;
import jakarta.ConstantsJakarta;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

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
    public Response getIdOrder(@PathParam(ConstantsJakarta.ID) Integer id) {
        Either<ApiError, Order> result = serv.getOrder(id);
        if (result.isRight()) {
            return Response.ok(result.get()).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result.getLeft()).build();
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
    public Response deleteOrder(@PathParam(ConstantsJakarta.ID) Integer id) {
        Either<ApiError, Integer> result = serv.delOrder(id);
        if (result.isRight()) {
            return Response.ok(result.get()).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
