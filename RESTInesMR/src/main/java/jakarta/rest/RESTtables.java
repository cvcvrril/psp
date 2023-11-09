package jakarta.rest;

import dao.modelo.TableRestaurant;
import domain.servicios.SERVtablesRestaurant;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/tables")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RESTtables {

    private SERVtablesRestaurant serv;

    @GET
    public TableRestaurant getAllTable() {
        return (TableRestaurant) serv.getAll();
    }

}
