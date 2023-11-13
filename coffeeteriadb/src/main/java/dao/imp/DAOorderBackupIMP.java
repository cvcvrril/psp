package dao.imp;

import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import model.Order;
import model.errors.ErrorCCustomer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Log4j2
public class DAOorderBackupIMP {

    private Path basePath = Path.of("data");

    private Path getFilePath(int customerId) {
        String fileName = "customer_" + customerId + ".xml";
        return basePath.resolve(fileName);
    }

    private void write(Path path, List<Order> lines) {
        try {
            Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public Either<ErrorCCustomer, Integer> backupOrders(int customerId, List<Order> orders) {
        Path filePath = getFilePath(customerId);
        try {
            write(filePath, orders);
            return Either.right(1);
        } catch (Exception e) {
            log.error("Error al realizar el respaldo de órdenes", e);
            return Either.left(new ErrorCCustomer("Error al realizar el respaldo de órdenes", 2));
        }
    }

    public Either<ErrorCCustomer, Integer> backupOrderItems(int customerId, List<String> orderItems) {
        Path filePath = getFilePath(customerId);
        try {
            write(filePath, orderItems);
            return Either.right(1);
        } catch (Exception e) {
            log.error("Error al realizar el respaldo de elementos de órdenes", e);
            return Either.left(new ErrorCCustomer("Error al realizar el respaldo de elementos de órdenes", 2));
        }
    }

    public Either<ErrorCCustomer, Integer> backupMenuItems(int customerId, List<String> menuItems) {
        Path filePath = getFilePath(customerId);
        try {
            write(filePath, menuItems);
            return Either.right(1);
        } catch (Exception e) {
            log.error("Error al realizar el respaldo de elementos de menú", e);
            return Either.left(new ErrorCCustomer("Error al realizar el respaldo de elementos de menú", 2));
        }
    }

}
