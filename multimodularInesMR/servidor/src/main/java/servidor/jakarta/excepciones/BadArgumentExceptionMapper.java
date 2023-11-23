package servidor.jakarta.excepciones;

import domain.errores.ApiError;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import servidor.domain.modelo.excepciones.BadArgumentException;

import java.time.LocalDateTime;

@Provider
public class BadArgumentExceptionMapper implements ExceptionMapper<BadArgumentException> {
    @Override
    public Response toResponse(BadArgumentException e) {
        ApiError apiError = new ApiError(e.getMessage(), LocalDateTime.now());
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity(apiError)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
