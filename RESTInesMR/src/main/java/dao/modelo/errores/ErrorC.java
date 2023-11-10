package dao.modelo.errores;

public abstract class ErrorC {

    private String message;
    private int numError;

    public ErrorC(String message, int numError) {
        this.message = message;
        this.numError = numError;
    }
}
