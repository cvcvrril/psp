package servidor.jakarta.filtros;

import domain.errores.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import servidor.jakarta.ConstantsJakarta;

import java.io.IOException;
import java.time.LocalDateTime;

@Provider
@Secure
public class FiltroLogin implements ContainerRequestFilter {

    @Context
    private HttpServletRequest request;
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if (request.getSession().getAttribute(ConstantsJakarta.LOGIN)==null){
            containerRequestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity(ApiError.builder().mensaje(ConstantsJakarta.ERROR_DE_FILTRO).fecha(LocalDateTime.now()).build())
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
    }
}
