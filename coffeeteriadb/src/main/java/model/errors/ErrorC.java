package model.errors;

public abstract class ErrorC {

    /*Atributos*/

    private String message;
    private int num_error;

    /*Constructor*/

    public ErrorC(String message, int num_error) {
        this.message = message;
        this.num_error = num_error;
    }
}
