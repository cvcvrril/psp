package domain.modelo;

public record MiEpisodio(int id, String name, String episode) {

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEpisode(){
        return episode;
    }
}
