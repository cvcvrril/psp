package dao.imp;

import common.Configuration;
import dao.DAOclients;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import model.Customer;
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

//TODO: cambiar todo esto de fiels al DB

@Log4j2
public class DAOclientsFILE implements DAOclients {

    private Path getPath() {
        return Paths.get(Configuration.getInstance().getPropertyTXT("pathDataCustomers"));
    }

    private List<Customer> read(Path path) throws IOException {
        List<Customer> customers = new ArrayList<>();
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                String[] trozo = line.split(";");
                if (trozo.length == 6) {
                    customers.add(new Customer(
                            Integer.parseInt(trozo[0]), trozo[1], trozo[2], trozo[3],
                            Integer.parseInt(trozo[4]), LocalDate.parse(trozo[5], form)
                    ));
                } else {
                    log.warn("Línea mal formateada: " + line);
                }
            }
        }
        return customers;
    }

    private void write(Path path, List<Customer> customers){
        try {
            List<String> updatedLines = new ArrayList<>();
            for (Customer customer : customers) {
                updatedLines.add(customer.toStringFile());
            }
            Files.write(path, updatedLines);
        }catch (IOException e){
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public Either<ErrorCCustomer, List<Customer>> getAll() {
        try {
            List<Customer> customers = read(getPath());
            return Either.right(customers);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorCCustomer("Error al leer el archivo", 2));
        }
    }

    @Override
    public Either<ErrorCCustomer, Customer> get(int i) {
        try {
            Path path = getPath();
            List<String> lines = Files.readAllLines(path);
            if (i >= 0 && i < lines.size()) {
                String line = lines.get(i);
                String[] trozo = line.split(";");
                DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                Customer customer = new Customer(
                        Integer.parseInt(trozo[0]), trozo[1], trozo[2], trozo[3],
                        Integer.parseInt(trozo[4]), LocalDate.parse(trozo[5], form)
                );
                return Either.right(customer);
            } else {
                return Either.left(new ErrorCCustomer("Cliente no encontrado", 1));
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorCCustomer("Error al leer el archivo", 2));
        }
    }

    @Override
    public Either<ErrorCCustomer, Integer> save(Customer t) {
        Path file = getPath();
        try {
            Files.write(file, (t.toStringFile() + "\n").getBytes(), StandardOpenOption.APPEND);
            return Either.right(1);
        } catch (IOException e) {
            log.error("Error al escribir el archivo", e);
            return Either.left(new ErrorCCustomer("Error al escribir el archivo", 2));
        }
    }

    @Override
    public Either<ErrorCCustomer, Integer> update(Customer t) {
        Path path = getPath();
        try {
            List<Customer> customers = read(path);
            DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (Customer customer : customers) {
                if (customer.getIdC() == t.getIdC()) {
                    customer.setFirstName(t.getFirstName());
                    customer.setSecondName(t.getSecondName());
                    customer.setEmail(t.getEmail());
                    customer.setPhoneNumber(t.getPhoneNumber());
                }
            }

            write(path, customers);

            return Either.right(1);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorCCustomer("Error al leer/escribir el archivo", 2));
        }
    }

    @Override
    public Either<ErrorCCustomer, Integer> delete(Customer t) {
        Path path = getPath();
        try {
            List<Customer> customers = read(path);
            customers.removeIf(client -> client.getIdC() == t.getIdC());
            write(path, customers);
            return Either.right(1);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorCCustomer("Error al leer/escribir el archivo", 2));
        }
    }
}
