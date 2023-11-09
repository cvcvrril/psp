package jakarta.rest;

import dao.modelo.TableRestaurant;
import domain.servicios.SERVtablesRestaurant;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/tables")
public class RESTtables {

    private final SERVtablesRestaurant serv;

    @Inject
    public RESTtables(SERVtablesRestaurant serv) {
        this.serv = serv;
    }

    @GET
    public TableRestaurant getAllTable(){
    return (TableRestaurant) serv.getAll();

    
}

}
