package domain.modelo;


public record MiLugar (int id, String name, String type, String dimension) {
    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getDimension(){
        return dimension;
    }

}
