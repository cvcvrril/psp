package jakarta.rest;

import dao.modelo.Order;
import domain.servicios.SERVorder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RESTorders {

    private SERVorder serv;

    @GET
    public Order getAllOrder(){
        return (Order) serv.getAll();
    }



}
