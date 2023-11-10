package domain.modelo.excepciones;

import jakarta.ws.rs.core.Response;

public class BaseCaidaException extends RuntimeException{
    public BaseCaidaException(String message){
        super(message);
    }

}
