package jakarta.excepciones;

import domain.modelo.excepciones.WrongObjectException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class WrongObjectExceptionMapper implements ExceptionMapper<WrongObjectException> {
    @Override
    public Response toResponse(WrongObjectException e) {
        return null;
    }
}
