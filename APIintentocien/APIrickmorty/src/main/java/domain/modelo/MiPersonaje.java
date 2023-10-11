package domain.modelo;

public record MiPersonaje(int id, String name, String status) {
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus(){
        return status;
    }

}
