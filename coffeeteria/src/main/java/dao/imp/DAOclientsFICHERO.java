package dao.imp;

import model.Client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DAOclientsFICHERO {

    private static Properties properties;

    public static void createFiles(){

        Path path1 = Paths.get(properties.getProperty("file_path"));

        try{
            Path createdFilePath = Files.createFile(path1);
        } catch (IOException e) {
            //Dios ha muerto y no me acuerdo de como loggear los errores
            throw new RuntimeException(e);
        }

    }

    public static List<Client> readFile(String file){
        createFiles();
        ArrayList<Client> aux = null;
        
        return null;
    }



}
