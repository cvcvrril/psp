package domain.modelo.excepciones;

public class BadArgumentException extends RuntimeException {
    public BadArgumentException(String mensaje){super(mensaje);}
}
