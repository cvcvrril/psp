package domain.modelo;

public record MiPersonaje(int id, String name) {
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
