package dao.retrofit.modelo.episodios;

import lombok.Data;

@Data
public class ResponseEpisodio {
    private final int id;
    private final String name;
    private final String air_date;
    private final String episode;
}
