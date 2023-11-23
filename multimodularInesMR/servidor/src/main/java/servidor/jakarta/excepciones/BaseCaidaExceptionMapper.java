package servidor.jakarta.excepciones;

import domain.errores.ApiError;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import servidor.domain.modelo.excepciones.BaseCaidaException;

import java.time.LocalDateTime;

@Provider
public class BaseCaidaExceptionMapper implements ExceptionMapper<BaseCaidaException> {
    @Override
    public Response toResponse(BaseCaidaException e) {
        ApiError apiError = new ApiError(e.getMessage(), LocalDateTime.now());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiError)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
