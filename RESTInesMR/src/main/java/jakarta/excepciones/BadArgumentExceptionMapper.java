package jakarta.excepciones;

import domain.modelo.excepciones.BadArgumentException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ExceptionMapper;

import java.time.LocalDateTime;

@Provider
public class BadArgumentExceptionMapper implements ExceptionMapper<BadArgumentException> {
    @Override
    public Response toResponse(BadArgumentException e) {
        ApiError error = new ApiError(e.getMessage(), LocalDateTime.now());
        return null;
    }
}
