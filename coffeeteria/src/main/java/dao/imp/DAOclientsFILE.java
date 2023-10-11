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

@Log4j2
public class DAOclientsFILE implements DAOclients {

    @Override
    public Either<ErrorCCustomer, List<Customer>> getAll() {
        Path path = Paths.get(Configuration.getInstance().getProperty("pathDataCustomers"));
        List<Customer> customers = new ArrayList<>();
        List<String> aux;
        try {
            aux = Files.readAllLines(path);
            DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            for (String line : aux){
                if (!line.trim().isEmpty()) {
                    String[] trozo = line.split(";");
                    if (trozo.length == 6) {
                        customers.add(new Customer(Integer.parseInt(trozo[0]), trozo[1], trozo[2], trozo[3], Integer.parseInt(trozo[4]), LocalDate.parse(trozo[5], form)));
                    } else {
                        log.warn("Línea mal formateada: " + line);
                    }
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return Either.right(customers);
    }

    @Override
    public Either<ErrorCCustomer, Customer> get(int i) {
        Path path = Paths.get(Configuration.getInstance().getProperty("pathDataCustomers"));
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
            if (i >= 0 && i < lines.size()) {
                String line = lines.get(i);
                String[] trozo = line.split(";");
                DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                Customer customer = new Customer(Integer.parseInt(trozo[0]), trozo[1], trozo[2], trozo[3], Integer.parseInt(trozo[4]), LocalDate.parse(trozo[5], form));
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
    public Either<ErrorCCustomer, Integer> update(Customer t) {
        Path path = Paths.get(Configuration.getInstance().getProperty("pathDataCustomers"));
        List<Customer> customers = new ArrayList<>();
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
            DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (String line : lines) {
                String[] trozo = line.split(";");
                Customer customer = new Customer(Integer.parseInt(trozo[0]), trozo[1], trozo[2], trozo[3], Integer.parseInt(trozo[4]), LocalDate.parse(trozo[5], form));

                if (customer.getIdC() == t.getIdC()) {
                    customer.setFirstName(t.getFirstName());
                    customer.setSecondName(t.getSecondName());
                    customer.setEmail(t.getEmail());
                    customer.setPhoneNumber(t.getPhoneNumber());
                }
                customers.add(customer);
            }
            List<String> updatedLines = new ArrayList<>();
            for (Customer customer : customers) {
                updatedLines.add(customer.toStringFile());
            }
            Files.write(path, updatedLines);
            return Either.right(1);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorCCustomer("Error al leer/escribir el archivo", 2));
        }
    }
    @Override
    public Either<ErrorCCustomer, Integer> delete(Customer t) {
        Path path = Paths.get(Configuration.getInstance().getProperty("pathDataCustomers"));
        List<Customer> customers = new ArrayList<>();
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
            DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            for (String line : lines) {
                String[] trozo = line.split(";");
                if (trozo.length == 6) {
                    Customer customer = new Customer(Integer.parseInt(trozo[0]), trozo[1], trozo[2], trozo[3], Integer.parseInt(trozo[4]), LocalDate.parse(trozo[5], form));
                    if (customer.getIdC() == t.getIdC()) {
                        customer.setFirstName(t.getFirstName());
                        customer.setSecondName(t.getSecondName());
                        customer.setEmail(t.getEmail());
                        customer.setPhoneNumber(t.getPhoneNumber());
                    }
                    customers.add(customer);
                } else {
                    log.warn("Línea mal formateada: " + line);
                }
            }
            customers.removeIf(client -> client.getIdC() == t.getIdC());
            List<String> updatedLines = new ArrayList<>();
            for (Customer customer : customers) {
                updatedLines.add(customer.toStringFile());
            }
            Files.write(path, updatedLines);
            return Either.right(1);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorCCustomer("Error al leer/escribir el archivo", 2));
        }
    }

}
