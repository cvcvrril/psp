package jakarta.rest;

import dao.modelo.Order;
import dao.modelo.errores.ErrorCOrder;
import domain.servicios.SERVorder;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RESTorders {

    private final SERVorder serv;

    @Inject
    public RESTorders(SERVorder serv) {
        this.serv = serv;
    }

    @GET
    public Response getAllOrder(){
        Either<ErrorCOrder,List<Order>> result = serv.getAll();
        return Response.ok(result.get()).build();
    }

    @GET
    @Path("/{id}")
    public Response getIdOrder(@PathParam("id") Integer id){
        Either<ErrorCOrder, Order> result = serv.getOrder(id);
        return Response.ok(result.get()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOrder(@PathParam("id")Integer id){
        Either<ErrorCOrder, Integer> result = serv.delOrder(id);
        if (result.isRight()){
            return Response.ok(result.get()).build();
        } else {
            return Response.status(500,"Dios ha muerto").build();
        }
    }
}
