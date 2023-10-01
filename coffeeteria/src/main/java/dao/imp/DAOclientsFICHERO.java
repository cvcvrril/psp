package dao.imp;

import common.Configuration;
import dao.DAOclients;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import model.Client;
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
        Path path = Paths.get(Configuration.getInstance().getProperty("pathDataCustomers"));
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
            if (i >= 0 && i < lines.size()) {
                String line = lines.get(i);
                String[] trozo = line.split(";");
                DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                Client client = new Client(Integer.parseInt(trozo[0]), trozo[1], trozo[2], trozo[3], Integer.parseInt(trozo[4]), LocalDate.parse(trozo[5], form));
                return Either.right(client);
            } else {
                return Either.left(new ErrorCCustomer("Cliente no encontrado", 1));
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorCCustomer("Error al leer el archivo", 2));
        }
    }

    @Override
    public Either<ErrorCCustomer, Integer> save(Client t) {
        Path file = Paths.get(Configuration.getInstance().getProperty("pathDataCustomers"));
        int error = 0;
        try {
            Files.write(file, (t.toStringFile()).getBytes(), StandardOpenOption.APPEND);
            error = 1;
        } catch (IOException e) {
            log.error("Error writing the file", e);
        }
        return Either.right(error);
    }


    @Override
    public Either<ErrorCCustomer, Integer> update(Client t) {
        Path path = Paths.get(Configuration.getInstance().getProperty("pathDataCustomers"));
        List<Client> clients = new ArrayList<>();
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
            DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (String line : lines) {
                String[] trozo = line.split(";");
                Client client = new Client(Integer.parseInt(trozo[0]), trozo[1], trozo[2], trozo[3], Integer.parseInt(trozo[4]), LocalDate.parse(trozo[5], form));

                if (client.getId_c() == t.getId_c()) {
                    client.setFirstName(t.getFirstName());
                    client.setSecondName(t.getSecondName());
                    client.setEmail(t.getEmail());
                    client.setPhoneNumber(t.getPhoneNumber());
                }

                clients.add(client);
            }

            List<String> updatedLines = new ArrayList<>();
            for (Client client : clients) {
                updatedLines.add(client.toStringFile());
            }

            Files.write(path, updatedLines);

            return Either.right(1); // Éxito al actualizar el cliente
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorCCustomer("Error al leer/escribir el archivo", 2));
        }
    }

    @Override
    public Either<ErrorCCustomer, Integer> delete(Client t) {
        Path path = Paths.get(Configuration.getInstance().getProperty("pathDataCustomers"));
        List<Client> clients = new ArrayList<>();
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
            DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (String line : lines) {
                String[] trozo = line.split(";");
                Client client = new Client(Integer.parseInt(trozo[0]), trozo[1], trozo[2], trozo[3], Integer.parseInt(trozo[4]), LocalDate.parse(trozo[5], form));
                clients.add(client);
            }

            clients.removeIf(client -> client.getId_c() == t.getId_c());

            List<String> updatedLines = new ArrayList<>();
            for (Client client : clients) {
                updatedLines.add(client.toStringFile());
            }

            Files.write(path, updatedLines);

            return Either.right(1); // Éxito al eliminar el cliente
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorCCustomer("Error al leer/escribir el archivo", 2));
        }
    }

}
