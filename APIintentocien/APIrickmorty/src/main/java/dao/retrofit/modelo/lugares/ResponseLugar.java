package dao.retrofit.modelo.lugares;

import lombok.Data;

@Data
public class ResponseLugar {
    private final int id;
    private final String name;
    private final String type;
    private final String dimension;
}
