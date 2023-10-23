package dao.imp;

import common.Configuration;
import dao.DAOorder;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import model.Order;
import model.errors.ErrorCOrder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
public class DAOorderFILE implements DAOorder {

    private Path getPath() {
        return Paths.get(Configuration.getInstance().getPropertyTXT("pathDataOrders"));
    }

    private List<Order> readOrdersFromFile(Path path) throws IOException {
        List<Order> orders = new ArrayList<>();
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        BufferedReader reader = Files.newBufferedReader(path);
        String line;
        while ((line = reader.readLine()) != null) {
            String[] trozo = line.split(";");
            orders.add(new Order(
                    Integer.parseInt(trozo[0]), LocalDate.parse(trozo[3], form).atStartOfDay(),
                    Integer.parseInt(trozo[2]), Integer.parseInt(trozo[1])
            ));
        }
        return orders;
    }

    private void writeOrderToFile(Path path, Order t) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND);
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String orderString = String.format(
                "%d;%d;%d;%s%n", t.getIdCo(), t.getIdOrd(),
                t.getIdTable(), t.getOrDate().format(form)
        );
        writer.write(orderString);
        writer.close();
    }

    private void writeOrdersToFile(Path path, List<Order> orders) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(path);
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Order t : orders) {
            String orderString = String.format(
                    "%d;%d;%d;%s%n", t.getIdCo(), t.getIdOrd(),
                    t.getIdTable(), t.getOrDate().format(form)
            );
            writer.write(orderString);
        }
        writer.close();
    }

    @Override
    public Either<ErrorCOrder, List<Order>> getAll() {
        try {
            List<Order> orders = readOrdersFromFile(getPath());
            return Either.right(orders);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorCOrder("Error al leer el archivo", 0));
        }
    }

    @Override
    public Either<ErrorCOrder, Order> get(int id) {
        try {
            List<Order> orders = readOrdersFromFile(getPath());
            for (Order order : orders) {
                if (order.getIdOrd() == id) {
                    return Either.right(order);
                }
            }
            return Either.left(new ErrorCOrder("Orden no encontrada", 0));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorCOrder("Error al leer el archivo", 0));
        }
    }

    @Override
    public Either<ErrorCOrder, Integer> save(Order t) {
        try {
            writeOrderToFile(getPath(), t);
            return Either.right(1);
        } catch (IOException e) {
            log.error("Error al escribir el archivo", e);
            return Either.left(new ErrorCOrder("Error al guardar la orden", 0));
        }
    }

    @Override
    public Either<ErrorCOrder, Integer> update(Order t) {
        try {
            List<Order> orders = readOrdersFromFile(getPath());
            for (Order order : orders) {
                if (order.getIdOrd() == t.getIdOrd()) {
                    order.setIdCo(t.getIdCo());
                    order.setIdTable(t.getIdTable());
                    order.setOrDate(t.getOrDate());
                }
            }
            writeOrdersToFile(getPath(), orders);
            return Either.right(1);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorCOrder("Error al leer/escribir el archivo", 0));
        }
    }

    @Override
    public Either<ErrorCOrder, Integer> delete(Order t) {
        try {
            List<Order> orders = readOrdersFromFile(getPath());
            orders.removeIf(order -> order.getIdOrd() == t.getIdOrd());
            writeOrdersToFile(getPath(), orders);
            return Either.right(1);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorCOrder("Error al leer/escribir el archivo", 0));
        }
    }
}
