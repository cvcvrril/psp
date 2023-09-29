package dao.imp;

import common.Configuration;
import dao.DAOclients;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import model.Client;
import model.errors.ErrorC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Log4j2
public class DAOclientsFICHERO implements DAOclients {


    @Override
    public Either<ErrorC, List<Client>> getAll() {
        Path path = Paths.get(Configuration.getInstance().getProperty("pathDataCustomers"));
        List<Client> clients = new ArrayList<>();
        List<String> aux;
        try {
            aux = Files.readAllLines(path);
            DateTimeFormatter form = DateTimeFormatter.ofPattern("YYYY-MM-DD");
            for (String line : aux){
                String[] trozo = line.split(";");
                clients.add(new Client(Integer.parseInt(trozo[0]), trozo[1], trozo[2], trozo[3], Integer.parseInt(trozo[5])));
            }
            System.out.println(aux);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return Either.right(clients);
    }

    @Override
    public Either<ErrorC, Client> get(int i) {
        return null;
    }

    @Override
    public Either<ErrorC, Integer> save(Client t) {
        return null;
    }


    @Override
    public Either<ErrorC, Integer> update(Client t) {
        return null;
    }

    @Override
    public Either<ErrorC, Integer> delete(Client t) {
        return null;
    }

}
