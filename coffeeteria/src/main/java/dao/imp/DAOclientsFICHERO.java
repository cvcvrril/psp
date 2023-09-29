package dao.imp;

import lombok.extern.log4j.Log4j2;
import model.Client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Log4j2
public class DAOclientsFICHERO {

    private static Properties properties;

    public static void createFiles(){
        Path path = Paths.get(properties.getProperty("pathDataCustomers"));
        List<String> aux;
        try {
            aux = Files.readAllLines(path);
            System.out.println(aux);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }


    }

    public static List<Client> readFile(String file){
        createFiles();
        ArrayList<Client> aux = null;

        return null;
    }



}
