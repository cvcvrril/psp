package domain.modelo.excepciones;

/**Se llama WrongObjectException porque sino habría conflicto con una excepción llamada ObjectNotFoundException**/

public class WrongObjectException extends RuntimeException{
    public WrongObjectException(String message){
        super(message);
    }
}
