package jakarta.excepciones;

import domain.modelo.excepciones.WrongObjectException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class WrongObjectExceptionMapper implements ExceptionMapper<WrongObjectException> {
    @Override
    public Response toResponse(WrongObjectException e) {
        ApiError apiError = new ApiError(e.getMessage(), LocalDateTime.now());
        return Response.status(Response.Status.NOT_FOUND).entity(apiError)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
