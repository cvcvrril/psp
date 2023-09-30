package dao.imp;

import common.Configuration;
import dao.DAOclients;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import model.Client;
import model.errors.ErrorC;
import model.errors.ErrorCCustomer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DAOclientsFICHERO implements DAOclients {

    @Override
    public Either<ErrorCCustomer, List<Client>> getAll() {
        Path path = Paths.get(Configuration.getInstance().getProperty("pathDataCustomers"));
        List<Client> clients = new ArrayList<>();
        List<String> aux;
        try {
            aux = Files.readAllLines(path);
            DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            for (String line : aux){
                String[] trozo = line.split(";");
                //Client/Customer =
                clients.add(new Client(Integer.parseInt(trozo[0]), trozo[1], trozo[2], trozo[3], Integer.parseInt(trozo[4]), LocalDate.parse(trozo[5], form)));
            }

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return Either.right(clients);
    }

    @Override
    public Either<ErrorCCustomer, Client> get(int i) {
        return null;
    }

    @Override
    public Either<ErrorCCustomer, Integer> save(Client t) {
        Path file = Paths.get(Configuration.getInstance().getProperty("pathDataCustomers"));
        int error = 0;
        try {
            Files.write(file, (t.toStringFile() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            error = 1;
        } catch (IOException e) {
            log.error("Error writing the file", e);
        }
        return Either.right(error);
    }


    @Override
    public Either<ErrorCCustomer, Integer> update(Client t) {
        return null;
    }

    @Override
    public Either<ErrorCCustomer, Integer> delete(Client t) {
        return null;
    }

}
